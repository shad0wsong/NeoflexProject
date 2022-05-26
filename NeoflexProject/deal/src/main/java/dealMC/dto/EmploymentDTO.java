package dealMC.dto;


import dealMC.enums.EmploymentPosition;
import dealMC.enums.EmploymentStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmploymentDTO {
    EmploymentStatus employmentStatus;
    String employerINN;
    BigDecimal salary;
    EmploymentPosition position;
    Integer workExperienceTotal;
    Integer workExperienceCurrent;

}
