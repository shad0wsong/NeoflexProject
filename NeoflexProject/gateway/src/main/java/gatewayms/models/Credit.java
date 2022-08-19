package gatewayms.models;



import gatewayms.enums.CreditStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Credit {

    Long credId;


    Application application;

    BigDecimal amount;
    Integer term;
    BigDecimal monthlyPayment;
    BigDecimal rate;
    BigDecimal psk;


    private List<PaymentScheduleElementEntity> paymentScheduleElements;

    Boolean isInsuranceEnabled;
    Boolean isSalaryClient;


    CreditStatus creditStatus;

    public Credit(Application application, BigDecimal amount, Integer term, BigDecimal monthlyPayment, BigDecimal rate, BigDecimal psk, List<PaymentScheduleElementEntity> paymentScheduleElements, Boolean isInsuranceEnabled, Boolean isSalaryClient, CreditStatus creditStatus) {
        this.application = application;
        this.amount = amount;
        this.term = term;
        this.monthlyPayment = monthlyPayment;
        this.rate = rate;
        this.psk = psk;
        this.paymentScheduleElements = paymentScheduleElements;
        this.isInsuranceEnabled = isInsuranceEnabled;
        this.isSalaryClient = isSalaryClient;
        this.creditStatus = creditStatus;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "credId=" + credId +
                "," +
                ", amount=" + amount +
                ", term=" + term +
                ", monthlyPayment=" + monthlyPayment +
                ", rate=" + rate +
                ", psk=" + psk +
                ", paymentScheduleElements=" + paymentScheduleElements +
                ", isInsuranceEnabled=" + isInsuranceEnabled +
                ", isSalaryClient=" + isSalaryClient +
                ", creditStatus=" + creditStatus +
                '}';
    }
}
