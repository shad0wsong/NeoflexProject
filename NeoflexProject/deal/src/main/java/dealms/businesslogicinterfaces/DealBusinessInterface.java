package dealms.businesslogicinterfaces;


import dealms.dto.EmailMessage;
import dealms.dto.LoanApplicationRequestDTO;
import dealms.dto.ScoringDataDTO;
import dealms.models.Application;
import dealms.models.Credit;

import java.util.LinkedHashMap;
import java.util.List;

public interface DealBusinessInterface {
    List<LinkedHashMap> getConveyorOffers(Application getApp, LoanApplicationRequestDTO loanApplicationRequestDTO);

    ScoringDataDTO setScoringData(ScoringDataDTO scoringDataDTO, Application application);

    Credit setCredit(ScoringDataDTO scoringDataDTO);

    void signDocs(Long applicationId, EmailMessage emailMessage);

    void rqSignDocs(Long applicationId, EmailMessage emailMessage);

    void sendDocs(EmailMessage emailMessage, Long applicationId);

    void createDocs(Long applicationId);
}
