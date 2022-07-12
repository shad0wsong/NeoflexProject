package dealms.businesslogic;

import dealms.dto.LoanApplicationRequestDTO;
import dealms.models.Application;
import dealms.models.Client;
import dealms.repoInterfaces.AppManagerInterface;
import dealms.repoInterfaces.ClientManagerInterface;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClientGeneratorTest {

    @Autowired
    ClientGenerator clientGenerator;

    @MockBean
    ClientManagerInterface clientManagerInterface;

    @MockBean
    AppManagerInterface appManagerInterface;

    @Test
    public void setAppAndClientAndSave() {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO
                (new BigDecimal(100000), 5, "test", "test", "test",
                        "test@mail.ru", LocalDate.now().minusYears(30), "1234", "123456");
        Client client = clientGenerator.setClient(loanApplicationRequestDTO);
        Application application = new Application();
        client.setApplication(application);
        application.setClient(client);
        clientManagerInterface.save(client);
        Application getApp = appManagerInterface.save(application);
        Assert.assertNotNull(application.getClient());
    }
}