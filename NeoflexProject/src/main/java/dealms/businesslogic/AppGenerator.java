package dealms.businesslogic;

import dealms.businesslogicinterfaces.AppGeneratorInt;
import dealms.exceptions.AppNotFoundExc;
import dealms.dto.LoanOfferDTO;
import dealms.models.Application;
import dealms.models.LoanOfferDtoEntity;
import dealms.models.StatusHistoryElement;
import dealms.repoInterfaces.AppManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static dealms.enums.AppStatus.PREAPPROVAL;

@Service
public class AppGenerator implements AppGeneratorInt {

    @Autowired
    AppManagerInterface appManagerInterface;


    public Application getAppByID(Long id) throws AppNotFoundExc{
        Optional<Application> optionalApplication = appManagerInterface.findById(id);
        Application getApp = Optional.ofNullable(optionalApplication.get()).orElseThrow(() -> new AppNotFoundExc("no such app"));
        return getApp;
    }

    public Application setAndSaveApp(LoanOfferDTO loanOfferDTO) throws AppNotFoundExc {
        Long appId = loanOfferDTO.getApplicationID();
        Optional<Application> application = appManagerInterface.findById(appId);
        Application realApp =Optional.ofNullable(application.get()).orElseThrow(() -> new AppNotFoundExc("no such app")) ;
        realApp.setAppStatus(PREAPPROVAL);
        realApp.getStatusHistoryElements().add(new StatusHistoryElement(PREAPPROVAL));
        realApp.setAppliedOffer(new LoanOfferDtoEntity(loanOfferDTO.getRequestedAmount(), loanOfferDTO.getTerm(),
                loanOfferDTO.getMonthlyPayment(), loanOfferDTO.getRate(), loanOfferDTO.getIsInsuranceEnabled(), loanOfferDTO.getIsSalaryClient(), realApp));
        Application returnApp=appManagerInterface.save(realApp);
        return returnApp;
    }
}
