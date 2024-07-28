package valuemakers.app.rentye.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import valuemakers.app.rentye.model.Contractor;
import valuemakers.app.rentye.model.ContractorType;
import valuemakers.app.rentye.repository.ContractorRepository;
import valuemakers.app.rentye.repository.ContractorTypeRepository;
import valuemakers.app.rentye.util.ServiceErrorException;

import java.util.Collection;

@Service
public class ContractorService {
    private final Validator validator;
    private final ContractorRepository contractorRepository;
    private final ContractorTypeRepository contractorTypeRepository;

    public ContractorService(Validator validator, ContractorRepository contractorRepository, ContractorTypeRepository contractorTypeRepository) {
        this.validator = validator;
        this.contractorRepository = contractorRepository;
        this.contractorTypeRepository = contractorTypeRepository;
    }

    public Collection<ContractorType> getAllContractorTypes() {
        return contractorTypeRepository.findAll();
    }

    public Collection<Contractor> getAllContractors() {
        return contractorRepository.findAll();
    }

    public Contractor getContractor(Long contractorId) {
        return contractorRepository.findById(contractorId).orElseThrow(() -> new ServiceErrorException("Could not find contractor " + contractorId));
    }

    public void addContractor(Contractor contractor) {
        if (contractor.getId() != null) {
            contractor.setId(null);
        }
        updateContractor(contractor);
    }

    public void updateContractor(Contractor contractor) {
        Long contractorId = contractor.getId();

        BindException errors = validateContractor(contractor);
        if (errors.hasErrors()) {
            throw new ServiceErrorException(errors.getAllErrors(),
                    contractorId == null ?
                            "Errors while adding new contractor" :
                            "Errors while editing contractor " + contractorId).initCause(errors);
        }
        try {
            contractorRepository.save(contractor);
        } catch (DataIntegrityViolationException ex) {
            throw (new ServiceErrorException(new ObjectError("contractor",
                    contractorId == null ?
                            "Constraint violation while adding new contractor" :
                            ("Constraint violation while updating contractor " + contractorId))).initCause(ex));
        }
    }

    public void deleteContractor(Long contractorId) {
        try {
            contractorRepository.delete(contractorRepository.findById(contractorId).orElseThrow(() -> new ServiceErrorException("Could not find contractor " + contractorId)));
        } catch (DataIntegrityViolationException ex) {
            throw(new ServiceErrorException(new ObjectError("apartment", "Constraint violation while deleting contractor " + contractorId)).initCause(ex));
        }
    }
    private BindException validateContractor(Contractor contractor) {
        BindException errors = new BindException(contractor, "contractor");
        validator.validate(contractor, errors);
        return errors;
    }
}
