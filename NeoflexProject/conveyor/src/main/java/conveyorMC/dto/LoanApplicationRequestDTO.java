package conveyormc.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoanApplicationRequestDTO {

    @NotNull(message = "Amount cannot be null")
    @Size(min=10000)
    BigDecimal amount;

    @NotNull(message = "Term cannot be null")
    Integer term;

    @Size(min = 2, max = 30, message
            = "firstname must be between 2 and 30 characters")
    String firstName;

    @Size(min = 2, max = 30, message
            = "lastname must be between 2 and 30 characters")
    String lastName;


    String middleName;

    @Email
    String email;

    LocalDate birthdayDate;

    @Size(min = 4, max = 4)
    String passportSeries;

    @Size(min = 6, max = 6)
    String passportNumber;


    @AssertTrue
    public boolean isValidMiddleName() {
        if (this.middleName == null) {
            return true;
        } else {
            return ((this.middleName.length() > 2) && (this.middleName.length() < 30)) ? false : true;
        }
    }

    @AssertTrue
    public boolean isValidBirthday() {

        return (LocalDate.now().getYear() - this.birthdayDate.getYear() < 18) ? false : true;
    }

}
