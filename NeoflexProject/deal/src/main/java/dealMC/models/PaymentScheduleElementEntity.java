package dealMC.models;


import dealMC.enums.CreditStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
public class PaymentScheduleElementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long PaymentScheduleElementId;

    Integer number;
    LocalDate date;
    BigDecimal totalPayment;
    BigDecimal interestPayment;
    BigDecimal debtPayment;
    BigDecimal remainingDebt;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "cred_id")
    Credit credit;


    public PaymentScheduleElementEntity(Integer number, LocalDate date, BigDecimal totalPayment, BigDecimal interestPayment, BigDecimal debtPayment, BigDecimal remainingDebt, Credit credit) {
        this.number = number;
        this.date = date;
        this.totalPayment = totalPayment;
        this.interestPayment = interestPayment;
        this.debtPayment = debtPayment;
        this.remainingDebt = remainingDebt;
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "PaymentScheduleElementEntity{" +
                "PaymentScheduleElementId=" + PaymentScheduleElementId +
                ", number=" + number +
                ", date=" + date +
                ", totalPayment=" + totalPayment +
                ", interestPayment=" + interestPayment +
                ", debtPayment=" + debtPayment +
                ", remainingDebt=" + remainingDebt +
                ","+
                '}';
    }
}
