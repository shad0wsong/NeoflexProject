package gatewayms.businessimpl;

import gatewayms.dto.EmailMessage;
import gatewayms.dto.LoanApplicationRequestDTO;
import gatewayms.dto.LoanOfferDTO;
import gatewayms.dto.ScoringDataDTO;
import gatewayms.models.Application;

import java.util.LinkedHashMap;
import java.util.List;

public interface RequestServiceImpl {
    List<LinkedHashMap> getDealOffers(LoanApplicationRequestDTO loanApplicationRequestDTO);

    void saveDealOffer(LoanOfferDTO loanOfferDTO);

    void calcCredit(ScoringDataDTO scoringDataDTO, Long appId);

    void sendDocs(Long applicationId, EmailMessage emailMessage);

    void signDocs(Long applicationId, EmailMessage emailMessage);

    void rqSignDocs(Long applicationId, EmailMessage emailMessage);

    Application getAppById(Long appId);

    Iterable getAllAp();
}
