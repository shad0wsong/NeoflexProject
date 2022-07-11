package dealms.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoanOfferDtoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long loanOfferDTOId;

    BigDecimal requestedAmount;
    Integer term;
    BigDecimal monthlyPayment;
    BigDecimal rate;
    Boolean isInsuranceEnabled;
    Boolean isSalaryClient;

    @OneToOne(mappedBy = "appliedOffer",cascade=CascadeType.ALL)
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
