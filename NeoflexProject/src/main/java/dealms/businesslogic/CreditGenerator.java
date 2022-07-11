package dealms.businesslogic;

import dealms.businesslogicinterfaces.CreditInt;
import dealms.models.Application;
import dealms.models.Credit;
import dealms.repoInterfaces.AppManagerInterface;
import dealms.repoInterfaces.CreditManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditGenerator implements CreditInt {

    @Autowired
    CreditManagerInterface creditManagerInterface;

    @Autowired
    AppManagerInterface appManagerInterface;

    public void setCreditAndAppAndSave(Credit credit, Application application) {
        credit.setApplication(application);
        application.setCredit(credit);
        creditManagerInterface.save(credit);
        appManagerInterface.save(application);
    }
}
