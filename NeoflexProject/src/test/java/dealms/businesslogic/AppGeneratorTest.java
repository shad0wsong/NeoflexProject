package dealms.businesslogic;

import dealms.dto.LoanOfferDTO;
import dealms.exceptions.AppNotFoundExc;
import dealms.models.Application;
import dealms.models.LoanOfferDtoEntity;
import dealms.models.StatusHistoryElement;
import dealms.repoInterfaces.AppManagerInterface;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static dealms.enums.AppStatus.PREAPPROVAL;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class AppGeneratorTest {
    @MockBean
    AppManagerInterface appManagerInterface;
    @Test
    void getAppByID() throws AppNotFoundExc {
       Long id=Long.valueOf(2);
        Optional<Application> optionalApplication = appManagerInterface.findById(id);
        assertEquals(optionalApplication,Optional.empty());
    }

    @Test
    void setAndSaveApp() throws AppNotFoundExc {
        LoanOfferDTO loanOfferDTO = new LoanOfferDTO(Long.valueOf(11), BigDecimal.valueOf(20000),
                5,BigDecimal.valueOf(4500),BigDecimal.valueOf(8),true,true);
        Long appId = loanOfferDTO.getApplicationID();
        Optional<Application> application = appManagerInterface.findById(appId);
        Application realApp = new Application();
        realApp.setAppStatus(PREAPPROVAL);
        realApp.setAppliedOffer(new LoanOfferDtoEntity(loanOfferDTO.getRequestedAmount(), loanOfferDTO.getTerm(),
                loanOfferDTO.getMonthlyPayment(), loanOfferDTO.getRate(), loanOfferDTO.getIsInsuranceEnabled(), loanOfferDTO.getIsSalaryClient(), realApp));
        appManagerInterface.save(realApp);
        assertNotNull(realApp);
    }
}