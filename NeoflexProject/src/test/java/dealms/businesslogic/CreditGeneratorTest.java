package dealms.businesslogic;

import dealms.models.Application;
import dealms.models.Credit;
import dealms.repoInterfaces.AppManagerInterface;
import dealms.repoInterfaces.CreditManagerInterface;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CreditGeneratorTest {

    @Autowired
    CreditGenerator creditGenerator;

    @MockBean
    CreditManagerInterface creditManagerInterface;

    @MockBean
    AppManagerInterface appManagerInterface;

    @Test
    void setCreditAndAppAndSave() {
        Credit credit = new Credit();
        Application application = new Application();
        credit.setApplication(application);
        application.setCredit(credit);
        Credit credit1=creditManagerInterface.save(credit);
        Application application1=appManagerInterface.save(application);
        Assert.assertNotNull(credit);
        Assert.assertNotNull(application);
    }
}