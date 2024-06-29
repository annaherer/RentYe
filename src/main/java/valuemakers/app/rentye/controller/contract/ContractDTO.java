package valuemakers.app.rentye.controller.contract;

import valuemakers.app.rentye.model.ContractPeriod;
import java.time.LocalDate;

public class ContractDTO {
    Long id;
    Boolean active;
    LocalDate startDate;
    LocalDate endDate;
    String mainTenant;
    ContractPeriod lastContractPeriod;

    public ContractDTO(Long id, Boolean active, LocalDate startDate, LocalDate endDate, String mainTenant, ContractPeriod lastContractPeriod) {
        this.id = id;
        this.active = active;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mainTenant = mainTenant;
        this.lastContractPeriod = lastContractPeriod;
    }

    public Long getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getMainTenant() {
        return mainTenant;
    }

    public ContractPeriod getLastContractPeriod() {
        return lastContractPeriod;
    }
}