package conveyormc.logicinterfaces;

import conveyormc.dto.ScoringDataDTO;

import java.math.BigDecimal;

public interface CalculationCreditInt {
    BigDecimal calcRate(ScoringDataDTO scoringDataDTO);

    BigDecimal calcPsk(ScoringDataDTO scoringDataDTO, BigDecimal monthly, Integer term);

    BigDecimal calcMonthly(ScoringDataDTO scoringDataDTO, BigDecimal rate, Integer term);

    BigDecimal calcMonthlyOffer(BigDecimal amount, BigDecimal rate, Integer term);
}
