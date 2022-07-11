package dealms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanApplicationRequestDTOValidationExc extends Exception {
    public LoanApplicationRequestDTOValidationExc(String message) {
        super(message);
    }
}
