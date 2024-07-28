package valuemakers.app.rentye.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import valuemakers.app.rentye.model.Apartment;
import valuemakers.app.rentye.model.ApartmentContractor;
import valuemakers.app.rentye.model.Contractor;
import valuemakers.app.rentye.model.ScheduledPayment;
import valuemakers.app.rentye.repository.ApartmentContractorRepository;
import valuemakers.app.rentye.repository.ApartmentRepository;
import valuemakers.app.rentye.repository.ContractorRepository;
import valuemakers.app.rentye.repository.ScheduledPaymentRepository;
import valuemakers.app.rentye.util.ServiceErrorException;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class ApartmentService {
    private final Validator validator;
    private final ApartmentRepository apartmentRepository;
    private final ApartmentContractorRepository apartmentContractorRepository;
    private final ScheduledPaymentRepository scheduledPaymentRepository;
    private final ContractorRepository contractorRepository;

    public ApartmentService(Validator validator, ApartmentRepository apartmentRepository, ApartmentContractorRepository apartmentContractorRepository, ScheduledPaymentRepository scheduledPaymentRepository, ContractorRepository contractorRepository) {
        this.validator = validator;
        this.apartmentRepository = apartmentRepository;
        this.apartmentContractorRepository = apartmentContractorRepository;
        this.scheduledPaymentRepository = scheduledPaymentRepository;
        this.contractorRepository = contractorRepository;
    }

    public Collection<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    public Apartment getApartment(Long apartmentId) {
        return apartmentRepository.findById(apartmentId).orElseThrow(() -> new ServiceErrorException("Could not find apartment " + apartmentId));
    }

    public void addApartment(Apartment apartment) {
        if (apartment.getId() != null) {
            apartment.setId(null);
        }
        updateApartment(apartment);
    }

    public void updateApartment(Apartment apartment) {
        Long apartmentId = apartment.getId();

        BindException errors = validateApartment(apartment);
        if (errors.hasErrors()) {
            throw new ServiceErrorException(errors.getAllErrors(),
                    apartmentId == null ?
                            "Errors while adding new apartment" :
                            "Errors while editing apartment " + apartmentId).initCause(errors);
        }
        try {
            apartmentRepository.save(apartment);
        } catch (DataIntegrityViolationException ex) {
            throw (new ServiceErrorException(new ObjectError("apartment",
                    apartmentId == null ?
                            "Constraint violation while adding new apartment" :
                            ("Constraint violation while updating apartment " + apartmentId))).initCause(ex));
        }
    }

    public void deleteApartment(Long apartmentId) {
        try {
            apartmentRepository.delete(apartmentRepository.findById(apartmentId).orElseThrow(() -> new ServiceErrorException("Could not find apartment " + apartmentId)));
        } catch (DataIntegrityViolationException ex) {
            throw(new ServiceErrorException(new ObjectError("apartment", "Constraint violation while deleting apartment " + apartmentId)).initCause(ex));
        }
    }

    public void addContractor(Long apartmentId, Long contractorId, Boolean settlePaymentsWithTenant) {
        Apartment apartment = getApartment(apartmentId);
        Contractor contractor = contractorRepository.findById(contractorId).orElseThrow(() -> new ServiceErrorException("Could not find contractor " + contractorId));

        if (apartment.getApartmentContractors().stream().map(ac -> ac.getContractor().getId()).anyMatch(c -> c.equals(contractorId)))
            throw new ServiceErrorException("Contractor must be unique within apartment");
        else {
            ApartmentContractor apartmentContractor = new ApartmentContractor();

            apartment.getApartmentContractors().add(apartmentContractor);
            apartmentContractor.setApartment(apartment);
            apartmentContractor.setContractor(contractor);
            apartmentContractor.setSettlePaymentsWithTenant(settlePaymentsWithTenant);
            try {
                apartmentContractorRepository.save(apartmentContractor);
            } catch (DataIntegrityViolationException ex) {
                throw(new ServiceErrorException("Constraint violation adding contractor to apartment " + apartmentId).initCause(ex));
            }
        }
    }

    public Boolean getContractorSettlePaymentsWithTenant(Long apartmentId, Long contractorId) {
        return findApartmentContractor(apartmentId, contractorId).getSettlePaymentsWithTenant();
    }

    public void setContractorSettlePaymentsWithTenant(Long apartmentId, Long contractorId, Boolean settlePaymentsWithTenant) {
        ApartmentContractor apartmentContractor = findApartmentContractor(apartmentId, contractorId);
        apartmentContractor.setSettlePaymentsWithTenant(settlePaymentsWithTenant);
        apartmentContractorRepository.save(apartmentContractor);
    }

    public void deleteContractor(Long apartmentId, Long contractorId) {
        ApartmentContractor apartmentContractor = findApartmentContractor(apartmentId, contractorId);
        try {
            apartmentContractor.getApartment().getApartmentContractors().remove(apartmentContractor);
            apartmentContractorRepository.delete(apartmentContractor);
        } catch (DataIntegrityViolationException ex) {
            throw(new ServiceErrorException("Constraint violation while deleting contractor " + contractorId + " from apartment " + apartmentId).initCause(ex));
        }

    }

    public void addScheduledPayment(Long apartmentId, Long contractorId, LocalDate paymentDate, Double paymentAmount) {
        ApartmentContractor apartmentContractor = findApartmentContractor(apartmentId, contractorId);
        ScheduledPayment scheduledPayment = new ScheduledPayment();
        scheduledPayment.setApartmentContractor(apartmentContractor);
        scheduledPayment.setAmount(paymentAmount);
        scheduledPayment.setDate(paymentDate);

        BindException errors = validateScheduledPayment(scheduledPayment);
        if (errors.hasErrors()) {
            throw new ServiceErrorException(errors.getAllErrors(), "Errors while adding scheduled payment for apartment " + apartmentId + " and contractor " + contractorId).initCause(errors);
        }
        try {
            scheduledPaymentRepository.save(scheduledPayment);
        } catch (DataIntegrityViolationException ex) {
            throw (new ServiceErrorException(new ObjectError("scheduledPayment","Constraint violation while adding scheduled payment for apartment " + apartmentId + " and contractor " + contractorId)).initCause(ex));
        }
    }

    public void deleteScheduledPayment(Long scheduledPaymentId) {
        ScheduledPayment scheduledPayment = scheduledPaymentRepository.findById(scheduledPaymentId).orElseThrow(() -> new ServiceErrorException("Could not find scheduled payment " + scheduledPaymentId));
        try {
            scheduledPayment.getApartmentContractor().getScheduledPayments().remove(scheduledPayment);
            scheduledPaymentRepository.delete(scheduledPayment);
        } catch (DataIntegrityViolationException ex) {
            throw(new ServiceErrorException("Constraint violation while deleting scheduled payment " + scheduledPaymentId).initCause(ex));
        }
    }

    private BindException validateApartment(Apartment apartment) {
        BindException errors = new BindException(apartment, "apartment");
        validator.validate(apartment, errors);
        if (apartment.getActive() && apartment.getDateSold()!=null)
            errors.addError(new FieldError("apartment", "dateSold", "Date sold can't be filled in for active apartment"));
        return errors;
    }

    private BindException validateScheduledPayment(ScheduledPayment scheduledPayment) {
        BindException errors = new BindException(scheduledPayment, "scheduledPayment");
        validator.validate(scheduledPayment, errors);
        return errors;
    }

    private ApartmentContractor findApartmentContractor(Long apartmentId, Long contractorId) {
        return apartmentContractorRepository.findByApartmentIdAndContractorId(apartmentId, contractorId)
                .orElseThrow(() -> new ServiceErrorException("Could not find contractor " + contractorId + " in apartment " + apartmentId));
    }
}
