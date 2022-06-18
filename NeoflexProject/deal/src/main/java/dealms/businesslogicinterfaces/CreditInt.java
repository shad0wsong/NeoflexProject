package dealms.businesslogicinterfaces;

import dealms.models.Application;
import dealms.models.Credit;

public interface CreditInt {
    void setCreditAndAppAndSave(Credit credit, Application application);
}
