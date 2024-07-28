package valuemakers.app.rentye.dto;

import lombok.Getter;
import valuemakers.app.rentye.model.ContractPeriod;
import java.time.LocalDate;

@Getter
public class ContractDTO {
    private final Long id;
    private final Boolean active;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String mainTenant;
    private final ContractPeriod lastContractPeriod;

    public ContractDTO(Long id, Boolean active, LocalDate startDate, LocalDate endDate, String mainTenant, ContractPeriod lastContractPeriod) {
        this.id = id;
        this.active = active;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mainTenant = mainTenant;
        this.lastContractPeriod = lastContractPeriod;
    }
}