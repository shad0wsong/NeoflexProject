package dealms.businesslogicinterfaces;


import dealms.dto.LoanApplicationRequestDTO;
import dealms.dto.ScoringDataDTO;
import dealms.models.Application;
import dealms.models.Credit;

import java.util.LinkedHashMap;
import java.util.List;

public interface DealBusinessInterface {
    List<LinkedHashMap> getConveyorOffers(Application getApp, LoanApplicationRequestDTO loanApplicationRequestDTO);

    ScoringDataDTO setScoringData(ScoringDataDTO scoringDataDTO, Application application);

    Credit setCredit( ScoringDataDTO scoringDataDTO);
}
