package conveyormc.dto;


import conveyormc.enums.EmploymentPosition;
import conveyormc.enums.EmploymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmploymentDTO {
    EmploymentStatus employmentStatus;
    String employerINN;
    BigDecimal salary;
    EmploymentPosition position;
    Integer workExperienceTotal;
    Integer workExperienceCurrent;

}
