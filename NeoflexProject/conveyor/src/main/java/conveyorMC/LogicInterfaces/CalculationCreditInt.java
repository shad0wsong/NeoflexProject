package conveyorMC.LogicInterfaces;

import conveyorMC.dto.ScoringDataDTO;

import java.math.BigDecimal;

public interface CalculationCreditInt {
    public BigDecimal calcRate(ScoringDataDTO scoringDataDTO);
    public BigDecimal calcPsk(ScoringDataDTO scoringDataDTO,BigDecimal monthly,Integer term);
    public BigDecimal calcMonthly(ScoringDataDTO scoringDataDTO,BigDecimal rate,Integer term);
    public BigDecimal calcMonthlyOffer(BigDecimal amount,BigDecimal rate,Integer term);
}
