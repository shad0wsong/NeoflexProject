package gatewayms.models;


import gatewayms.enums.AppStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Application {

    Long appId;


    Client client;


    Credit credit;


    AppStatus appStatus;

    LocalDate creationDate;


    LoanOfferDtoEntity appliedOffer;

    LocalDate signDate;

    String sesCode;


    List<StatusHistoryElement> statusHistoryElements;

    public Application(Client client, Credit credit, AppStatus appStatus, LocalDate creationDate, LoanOfferDtoEntity appliedOffer, LocalDate signDate, String sesCode, List<StatusHistoryElement> statusHistoryElements) {
        this.client = client;
        this.credit = credit;
        this.appStatus = appStatus;
        this.creationDate = creationDate;
        this.appliedOffer = appliedOffer;
        this.signDate = signDate;
        this.sesCode = sesCode;
        this.statusHistoryElements = statusHistoryElements;
    }

    public Application() {
    }

    @Override
    public String toString() {
        return "Application{" +
                "appId=" + appId +
                "," +
                "," +
                ", appStatus=" + appStatus +
                ", creationDate=" + creationDate +
                "," +
                ", signDate=" + signDate +
                ", sesCode='" + sesCode + '\'' +
                ", statusHistoryElements=" + statusHistoryElements +
                '}';
    }
}
