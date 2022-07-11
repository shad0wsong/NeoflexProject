package dealms.models;

import dealms.enums.AppStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusHistoryElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long statusHistoryElementId;

    @Enumerated(EnumType.STRING)
    AppStatus appStatus;

    @ManyToMany(mappedBy = "statusHistoryElements",cascade=CascadeType.ALL)
    List<Application> applicationList;

    public StatusHistoryElement(AppStatus appStatus, List<Application> applicationList) {
        this.appStatus = appStatus;
        this.applicationList = applicationList;
    }

    public StatusHistoryElement(AppStatus appStatus) {
        this.appStatus = appStatus;
    }

    @Override
    public String toString() {
        return "StatusHistoryElement{" +
                "statusHistoryElementId=" + statusHistoryElementId +
                ", appStatus=" + appStatus +
                "," +
                '}';
    }
}
