package conveyorMC.LogicInterfaces;

import conveyorMC.dto.ScoringDataDTO;

import java.math.BigDecimal;

public interface CalculationCreditInt {
    public BigDecimal calcRate(ScoringDataDTO scoringDataDTO);
    public BigDecimal calcPsk(ScoringDataDTO scoringDataDTO,BigDecimal rate);
    public BigDecimal calcMonthly(ScoringDataDTO scoringDataDTO,BigDecimal rate,Integer term);
}
