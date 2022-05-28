package dealMC.BusinessLogicInterfaces;


import dealMC.dto.LoanApplicationRequestDTO;
import dealMC.dto.ScoringDataDTO;
import dealMC.models.Application;
import dealMC.models.Credit;

import java.util.LinkedHashMap;
import java.util.List;

public interface DealBusinessInterface {
    List<LinkedHashMap> getConveyorOffers(Application getApp, LoanApplicationRequestDTO loanApplicationRequestDTO);

    ScoringDataDTO setScoringData(ScoringDataDTO scoringDataDTO, Application application);

    Credit setCredit(Application application, ScoringDataDTO scoringDataDTO);
}
