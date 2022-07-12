package gatewayms.models;


import gatewayms.enums.AppStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusHistoryElement {

    Long statusHistoryElementId;


    AppStatus appStatus;


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
