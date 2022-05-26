package dealMC.BusinessLogicInterfaces;


import dealMC.dto.LoanApplicationRequestDTO;
import dealMC.dto.LoanOfferDTO;
import dealMC.dto.ScoringDataDTO;
import dealMC.models.Application;
import dealMC.models.Credit;

import java.util.LinkedHashMap;
import java.util.List;

public interface DealBusinessInterface {
    public List<LinkedHashMap> getConveyorOffers(Application getApp, LoanApplicationRequestDTO loanApplicationRequestDTO);
    public ScoringDataDTO setScoringData(ScoringDataDTO scoringDataDTO, Application application);
    public Credit setCredit(Application application, ScoringDataDTO scoringDataDTO);
}
