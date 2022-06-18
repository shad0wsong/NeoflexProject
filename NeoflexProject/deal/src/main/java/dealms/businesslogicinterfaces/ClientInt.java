package dealms.businesslogicinterfaces;

import dealms.dto.LoanApplicationRequestDTO;
import dealms.models.Application;
import dealms.models.Client;

public interface ClientInt {
    Client setClient(LoanApplicationRequestDTO loanApplicationRequestDTO);

    void setAppAndClientAndSave(Client client, Application application);
}
