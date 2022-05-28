package dealMC.LogicInterfaces;


import dealMC.dto.ScoringDataDTO;

import java.math.BigDecimal;

public interface CalculationCreditInt {
    BigDecimal calcRate(ScoringDataDTO scoringDataDTO);

    BigDecimal calcPsk(ScoringDataDTO scoringDataDTO, BigDecimal rate);

    BigDecimal calcMonthly(ScoringDataDTO scoringDataDTO, BigDecimal psk);
}
