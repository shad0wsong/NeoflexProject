package dealms.dto;


import dealms.enums.EmailTheme;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailMessage {
    String address;
    EmailTheme theme;
    Long applicationId;
}
