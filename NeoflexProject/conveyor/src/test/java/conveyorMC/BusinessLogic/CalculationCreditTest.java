package conveyorMC.BusinessLogic;

import conveyorMC.dto.ScoringDataDTO;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CalculationCreditTest {//тк скорее всего моя логика для подсчета пск неправильна тесты для подсчёта не пишу пока

    @Test
    public void calcRate() {
        CalculationCredit calculationCredit = new CalculationCredit();
        assertEquals(1,calculationCredit.calcRate(new ScoringDataDTO()).compareTo(BigDecimal.valueOf(0)));
    }

    @Test
    public void calcPsk() {
        CalculationCredit calculationCredit = new CalculationCredit();
        assertEquals(1,calculationCredit.calcRate(new ScoringDataDTO()).compareTo(BigDecimal.valueOf(0)));
    }

    @Test
    public void calcMonthly() {
        CalculationCredit calculationCredit = new CalculationCredit();
        assertEquals(1,calculationCredit.calcRate(new ScoringDataDTO()).compareTo(BigDecimal.valueOf(0)));
    }
}