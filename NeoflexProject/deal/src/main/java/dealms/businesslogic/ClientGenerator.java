package dealms.businesslogic;

import dealms.businesslogicinterfaces.ClientInt;
import dealms.dto.LoanApplicationRequestDTO;
import dealms.models.Application;
import dealms.models.Client;
import dealms.repoInterfaces.AppManagerInterface;
import dealms.repoInterfaces.ClientManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientGenerator implements ClientInt {

    @Autowired
    ClientManagerInterface clientManagerInterface;

    @Autowired
    AppManagerInterface appManagerInterface;

    public Client setClient(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        Client client = new Client(null, loanApplicationRequestDTO.getFirstName(), loanApplicationRequestDTO.getLastName(), loanApplicationRequestDTO.getMiddleName(),
                loanApplicationRequestDTO.getEmail(), loanApplicationRequestDTO.getBirthdayDate(),
                null, null, null, null, null,
                loanApplicationRequestDTO.getPassportSeries(), loanApplicationRequestDTO.getPassportNumber(), null,
                null, null, null, null, null, null);
        return client;

    }

    public void setAppAndClientAndSave(Client client, Application application) {
        client.setApplication(application);
        application.setClient(client);
        clientManagerInterface.save(client);
        appManagerInterface.save(application);
    }
}
