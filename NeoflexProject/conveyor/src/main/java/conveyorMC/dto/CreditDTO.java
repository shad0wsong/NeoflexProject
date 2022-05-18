package conveyorMC.dto;


import conveyorMC.LogicInterfaces.Validate;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditDTO {
BigDecimal amount;
Integer term;
BigDecimal monthlyPayment;
BigDecimal rate;
BigDecimal psk;
Boolean isInsuranceEnabled;
Boolean isSalaryClient;
List<PaymentScheduleElement> paymentSchedule;


}
