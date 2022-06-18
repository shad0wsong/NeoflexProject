package dealms.businesslogicinterfaces;

import dealms.exceptions.AppNotFoundExc;
import dealms.dto.LoanOfferDTO;
import dealms.models.Application;

public interface AppGeneratorInt {
    Application getAppByUUID(Application application) throws AppNotFoundExc;
    void setAndSaveApp(LoanOfferDTO loanOfferDTO) throws AppNotFoundExc;
    Application getAppByID(Long id) throws AppNotFoundExc;
}
