package conveyormc.logicinterfaces;

import conveyormc.dto.PaymentScheduleElement;
import conveyormc.dto.ScoringDataDTO;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentScheduleInt {
    List<PaymentScheduleElement> getPaymentSchedule(ScoringDataDTO scoringDataDTO, BigDecimal monthlyPayment);
}
