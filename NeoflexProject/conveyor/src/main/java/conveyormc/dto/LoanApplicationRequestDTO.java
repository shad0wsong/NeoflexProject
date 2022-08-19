package conveyormc.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoanApplicationRequestDTO {

    @DecimalMin("10000")
    BigDecimal amount;


    @Min(5)
    Integer term;

    @Size(min = 2, max = 30, message
            = "firstname must be between 2 and 30 characters")
    String firstName;

    @Size(min = 2, max = 30, message
            = "lastname must be between 2 and 30 characters")
    String lastName;

    @Size(min = 2, max = 30, message
            = "middleName must be between 2 and 30 characters")
    String middleName;

    @Email
    String email;

    LocalDate birthdayDate;

    @Size(min = 4, max = 4)
    String passportSeries;

    @Size(min = 6, max = 6)
    String passportNumber;



    @AssertTrue
    public boolean isValidBirthday() {

        return (LocalDate.now().getYear() - this.birthdayDate.getYear() > 18);
    }

}
