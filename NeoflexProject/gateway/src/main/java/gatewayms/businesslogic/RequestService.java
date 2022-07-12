package gatewayms.businesslogic;

import gatewayms.businessimpl.RequestServiceImpl;
import gatewayms.dto.EmailMessage;
import gatewayms.dto.LoanApplicationRequestDTO;
import gatewayms.dto.LoanOfferDTO;
import gatewayms.dto.ScoringDataDTO;
import gatewayms.models.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class RequestService implements RequestServiceImpl {
    @Autowired
    RestTemplate restTemplate;

    @Value("${offer}")
    String offer;

    @Value("${app}")
    String app;

    @Value("${calc}")
    String calc;

    @Value("${doc}")
    String doc;

    @Value("${allap}")
    String allAp;


    public List<LinkedHashMap> getDealOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        List<LinkedHashMap> offers = restTemplate.postForObject(app, loanApplicationRequestDTO, List.class);
        return offers;
    }

    public void saveDealOffer(LoanOfferDTO loanOfferDTO) {
        restTemplate.put(offer, loanOfferDTO);
    }

    public void calcCredit(ScoringDataDTO scoringDataDTO, Long appId) {
        restTemplate.put(calc + appId, scoringDataDTO);
    }

    public void sendDocs(Long applicationId, EmailMessage emailMessage) {
        restTemplate.postForObject(doc + applicationId + "/send", emailMessage, Object.class);
    }

    public void signDocs(Long applicationId, EmailMessage emailMessage) {
        restTemplate.postForObject(doc+ applicationId + "/code", emailMessage, Object.class);
    }

    public void rqSignDocs(Long applicationId, EmailMessage emailMessage) {
        restTemplate.postForObject(doc + applicationId + "/sign", emailMessage, Object.class);
    }

    public Application getAppById(Long appId){
        Application application=restTemplate.getForObject(allAp+appId,Application.class);
        return application;
    }
    public Iterable getAllAp(){
        Iterable<Application> applications=restTemplate.getForObject(allAp,Iterable.class);
        return applications;
    }
}
