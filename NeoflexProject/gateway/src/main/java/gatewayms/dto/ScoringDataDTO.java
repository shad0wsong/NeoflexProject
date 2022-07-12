package gatewayms.dto;


import gatewayms.enums.Gender;
import gatewayms.enums.MaritalStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScoringDataDTO {

    BigDecimal amount;
    Integer term;
    String firstName;
    String lastName;
    String middleName;
    Gender gender;
    LocalDate birthday;
    String passportSeries;
    String passportNumber;
    LocalDate passportIssueDate;
    String passportIssueBranch;
    MaritalStatus maritalStatus;
    Integer dependentAmount;
    EmploymentDTO employment;
    String account;
    Boolean isInsuranceEnabled;
    Boolean isSalaryClient;
}
