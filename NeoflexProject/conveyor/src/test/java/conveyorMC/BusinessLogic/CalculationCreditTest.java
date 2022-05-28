package conveyorMC.BusinessLogic;

import conveyorMC.dto.EmploymentDTO;
import conveyorMC.dto.ScoringDataDTO;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import static conveyorMC.enums.EmploymentPosition.TOP_MANAGER;
import static conveyorMC.enums.EmploymentStatus.EMPLOYED;
import static conveyorMC.enums.Gender.MALE;
import static conveyorMC.enums.MaritalStatus.MARRIED;
import static org.junit.Assert.*;

public class CalculationCreditTest {

    @Test
    public void calcRate() {
        CalculationCredit calculationCredit = new CalculationCredit();
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO(BigDecimal.valueOf(100000),5,"test","test",
                "test",MALE, LocalDate.now().minusYears(30),"1234","123456",LocalDate.now(),"test",MARRIED,
                0,new EmploymentDTO(EMPLOYED,"123",BigDecimal.valueOf(100000),TOP_MANAGER,20,20),"12312",true,true);
        BigDecimal actual = calculationCredit.calcRate(scoringDataDTO);
        assertEquals(BigDecimal.valueOf(0.08),actual);
    }

    @Test
    public void calcPsk() {
        CalculationCredit calculationCredit = new CalculationCredit();
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO(BigDecimal.valueOf(100000),5,"test","test",
                "test",MALE, LocalDate.now().minusYears(30),"1234","123456",LocalDate.now(),"test",MARRIED,
                0,new EmploymentDTO(EMPLOYED,"123",BigDecimal.valueOf(100000),TOP_MANAGER,20,20),"12312",true,true);
        BigDecimal actual = calculationCredit.calcPsk(scoringDataDTO,new BigDecimal(25045.645456700000),scoringDataDTO.getTerm());

        assertEquals("0",actual.toPlainString());
    }

    @Test
    public void calcMonthly() {
        CalculationCredit calculationCredit = new CalculationCredit();
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO(BigDecimal.valueOf(100000),5,"test","test",
                "test",MALE, LocalDate.now().minusYears(30),"1234","123456",LocalDate.now(),"test",MARRIED,
                0,new EmploymentDTO(EMPLOYED,"123",BigDecimal.valueOf(100000),TOP_MANAGER,20,20),"12312",true,true);
        BigDecimal actual = calculationCredit.calcMonthly(scoringDataDTO,new BigDecimal(0.08),scoringDataDTO.getTerm());
        NumberFormat formatter = new DecimalFormat("#0");
        assertEquals(new BigDecimal(25046).toPlainString(),formatter.format(actual));
    }
}