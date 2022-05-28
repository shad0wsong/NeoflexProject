package dealMC.models;

import dealMC.enums.AppStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Entity
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long appId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credId")
    Credit credit;

    @Enumerated(EnumType.STRING)
    AppStatus appStatus;

    LocalDate creationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loanOfferDTOId")
    LoanOfferDtoEntity appliedOffer;

    LocalDate signDate;

    String sesCode;

    String UUID;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "application_history",
            joinColumns = @JoinColumn(name = "appId"),
            inverseJoinColumns = @JoinColumn(name = "statusHistoryElementId")
    )
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
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        this.UUID = new String(array, Charset.forName("UTF-8"));
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
                ", UUID='" + UUID + '\'' +
                ", statusHistoryElements=" + statusHistoryElements +
                '}';
    }
}
