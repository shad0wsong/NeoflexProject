package gatewayms.models;

import lombok.*;
import lombok.experimental.FieldDefaults;


import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoanOfferDtoEntity {

    Long loanOfferDTOId;

    BigDecimal requestedAmount;
    Integer term;
    BigDecimal monthlyPayment;
    BigDecimal rate;
    Boolean isInsuranceEnabled;
    Boolean isSalaryClient;


    Application application;

    public LoanOfferDtoEntity(BigDecimal requestedAmount, Integer term, BigDecimal monthlyPayment, BigDecimal rate, Boolean isInsuranceEnabled, Boolean isSalaryClient, Application application) {
        this.requestedAmount = requestedAmount;
        this.term = term;
        this.monthlyPayment = monthlyPayment;
        this.rate = rate;
        this.isInsuranceEnabled = isInsuranceEnabled;
        this.isSalaryClient = isSalaryClient;
        this.application = application;
    }

    @Override
    public String toString() {
        return "LoanOfferDtoEntity{" +
                "loanOfferDTOId=" + loanOfferDTOId +
                ", requestedAmount=" + requestedAmount +
                ", term=" + term +
                ", monthlyPayment=" + monthlyPayment +
                ", rate=" + rate +
                ", isInsuranceEnabled=" + isInsuranceEnabled +
                ", isSalaryClient=" + isSalaryClient +
                "," +
                '}';
    }
}
