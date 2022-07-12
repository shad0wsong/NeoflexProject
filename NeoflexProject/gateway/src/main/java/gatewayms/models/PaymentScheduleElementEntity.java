package gatewayms.models;


import lombok.*;
import lombok.experimental.FieldDefaults;


import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentScheduleElementEntity {

    Long PaymentScheduleElementId;

    Integer number;
    LocalDate date;
    BigDecimal totalPayment;
    BigDecimal interestPayment;
    BigDecimal debtPayment;
    BigDecimal remainingDebt;


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
                "," +
                '}';
    }
}
