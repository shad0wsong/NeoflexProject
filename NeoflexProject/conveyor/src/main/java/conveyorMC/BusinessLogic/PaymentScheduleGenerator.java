package conveyormc.businesslogic;

import conveyormc.dto.PaymentScheduleElement;
import conveyormc.dto.ScoringDataDTO;
import conveyormc.logicinterfaces.PaymentScheduleInt;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentScheduleGenerator implements PaymentScheduleInt {
    public List<PaymentScheduleElement> getPaymentSchedule(ScoringDataDTO scoringDataDTO, BigDecimal monthlyPayment) {
        List<PaymentScheduleElement> paymentScheduleElements = new ArrayList<>();
        for (int i = 1; i <= scoringDataDTO.getTerm(); i++) {
            BigDecimal remainingDebt = scoringDataDTO.getAmount().subtract(monthlyPayment.multiply(BigDecimal.valueOf(i - 1)));
            if (remainingDebt.intValue() < 0) {
                remainingDebt = new BigDecimal(0);
            }
            paymentScheduleElements.add(new PaymentScheduleElement(i, LocalDate.now()
                    .plusMonths(i), scoringDataDTO.getAmount(), monthlyPayment,
                    monthlyPayment, remainingDebt));
        }
        return paymentScheduleElements;
    }
}
