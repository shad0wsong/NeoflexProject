package dealms.models;


import dealms.enums.CreditStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long credId;

    @OneToOne(mappedBy = "credit",cascade=CascadeType.ALL)
    Application application;

    BigDecimal amount;
    Integer term;
    BigDecimal monthlyPayment;
    BigDecimal rate;
    BigDecimal psk;

    @OneToMany(mappedBy = "credit", fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private List<PaymentScheduleElementEntity> paymentScheduleElements;

    Boolean isInsuranceEnabled;
    Boolean isSalaryClient;

    @Enumerated(EnumType.STRING)
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
