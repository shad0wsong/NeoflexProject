package conveyormc.logicinterfaces;

import conveyormc.dto.ScoringDataDTO;
import conveyormc.exceptions.LoanApplicationRequestDTOValidationExc;

import java.math.BigDecimal;

public interface CalculationCreditInt {
    BigDecimal calcRate(ScoringDataDTO scoringDataDTO) throws LoanApplicationRequestDTOValidationExc;

    BigDecimal calcPsk(ScoringDataDTO scoringDataDTO, BigDecimal monthly, Integer term);

    BigDecimal calcMonthly(ScoringDataDTO scoringDataDTO, BigDecimal rate, Integer term);

    BigDecimal calcMonthlyOffer(BigDecimal amount, BigDecimal rate, Integer term);
}
