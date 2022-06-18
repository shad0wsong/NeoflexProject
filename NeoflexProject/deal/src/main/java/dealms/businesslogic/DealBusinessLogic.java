package dealms.businesslogic;

import dealms.businesslogicinterfaces.DealBusinessInterface;
import dealms.dto.CreditDTO;
import dealms.dto.LoanApplicationRequestDTO;
import dealms.dto.ScoringDataDTO;
import dealms.models.Application;
import dealms.models.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

import static dealms.enums.CreditStatus.CALCULATED;

@Service
public class DealBusinessLogic implements DealBusinessInterface {

    @Value("${offers}")
    String offers;

    @Value("${calc}")
    String calc;

    @Autowired
    RestTemplate restTemplate;

    public List<LinkedHashMap> getConveyorOffers(Application getApp, LoanApplicationRequestDTO loanApplicationRequestDTO) {

        List<LinkedHashMap> loanOfferDTOList = restTemplate.postForObject(offers, loanApplicationRequestDTO, List.class);
        for (int i = 0; i < loanOfferDTOList.size(); i++) {
            loanOfferDTOList.get(i).put("applicationID", getApp.getAppId());
        }
        return loanOfferDTOList;
    }

    public ScoringDataDTO setScoringData(ScoringDataDTO scoringDataDTO, Application application) {
        scoringDataDTO.setFirstName(application.getClient().getFirstName());
        scoringDataDTO.setLastName(application.getClient().getLastName());
        scoringDataDTO.setMiddleName(application.getClient().getMiddleName());
        scoringDataDTO.setGender(application.getClient().getGender());
        scoringDataDTO.setBirthday(application.getClient().getBirthdayDate());
        scoringDataDTO.setPassportSeries(application.getClient().getPassportSeries());
        scoringDataDTO.setPassportNumber(application.getClient().getPassportNumber());
        scoringDataDTO.setPassportIssueDate(application.getClient().getIssue_date());
        scoringDataDTO.setPassportIssueBranch(application.getClient().getIssue_branch());
        scoringDataDTO.setMaritalStatus(application.getClient().getMaritalStatus());
        return scoringDataDTO;
    }

    public Credit setCredit(Application application, ScoringDataDTO scoringDataDTO) {

        CreditDTO creditDTO = restTemplate.postForObject(calc, scoringDataDTO, CreditDTO.class);
        Credit credit = new Credit();
        credit.setCreditStatus(CALCULATED);
        credit.setAmount(creditDTO.getAmount());
        credit.setIsInsuranceEnabled(creditDTO.getIsInsuranceEnabled());
        credit.setIsSalaryClient(creditDTO.getIsSalaryClient());
        credit.setPsk(creditDTO.getPsk());
        credit.setTerm(creditDTO.getTerm());
        credit.setMonthlyPayment(creditDTO.getMonthlyPayment());
        credit.setRate(creditDTO.getRate());

        return credit;
    }
}
