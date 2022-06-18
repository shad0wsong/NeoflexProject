package conveyormc.logicinterfaces;

import conveyormc.dto.CreditDTO;
import conveyormc.dto.PaymentScheduleElement;
import conveyormc.dto.ScoringDataDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CreditDTOInt {
    CreditDTO setCreditInfo(ScoringDataDTO scoringDataDTO, BigDecimal monthlyPayment, BigDecimal rate, BigDecimal psk, List<PaymentScheduleElement> paymentScheduleElements);
}
