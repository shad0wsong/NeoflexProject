package conveyorMC.controllers;

import conveyorMC.BusinessLogic.CalculationCredit;
import conveyorMC.dto.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static conveyorMC.enums.EmploymentPosition.TOP_MANAGER;
import static conveyorMC.enums.EmploymentStatus.EMPLOYED;
import static conveyorMC.enums.Gender.MALE;
import static conveyorMC.enums.MaritalStatus.MARRIED;
import static org.junit.Assert.*;

public class ConveyorControllerTest {

    @Test
    public void actual() {
        CalculationCredit calculationCredit =  new CalculationCredit();
        BigDecimal rate=new BigDecimal(0.08);
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO
                (new BigDecimal(100000),5,"test","test","test",
                        "test@mail.ru", LocalDate.now(),"1234","123456");
        List<LoanOfferDTO> actual = new ArrayList<>();
        actual.add(new LoanOfferDTO(null, loanApplicationRequestDTO.getAmount(),
                loanApplicationRequestDTO.getTerm(), calculationCredit.calcMonthlyOffer(loanApplicationRequestDTO.getAmount(),rate.subtract(BigDecimal.valueOf(0.01)), loanApplicationRequestDTO.getTerm()),
                rate.subtract(BigDecimal.valueOf(0.01)), true, false));

        assertEquals(Optional.ofNullable(actual.get(0).getTerm()),5);
        assertEquals(Optional.ofNullable(actual.get(0).getRate()),new BigDecimal(0.07));
        assertEquals(Optional.ofNullable(actual.get(0).getMonthlyPayment()),new BigDecimal(24389.069444137407));
    }

    @Test
    public void calculation() {
        CalculationCredit calculationCredit = new CalculationCredit();
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO(BigDecimal.valueOf(100000),5,"test","test",
                "test",MALE,LocalDate.now().minusYears(30),"1234","123456",LocalDate.now(),"test",MARRIED,
                0,new EmploymentDTO(EMPLOYED,"123",BigDecimal.valueOf(100000),TOP_MANAGER,20,20),"12312",true,true);
        CreditDTO creditDTO = new CreditDTO();
        BigDecimal rate = calculationCredit.calcRate(scoringDataDTO);

        BigDecimal monthlyPayment = calculationCredit.calcMonthly(scoringDataDTO, rate,scoringDataDTO.getTerm());

        BigDecimal psk = calculationCredit.calcPsk(scoringDataDTO, monthlyPayment, scoringDataDTO.getTerm());

        creditDTO.setAmount(scoringDataDTO.getAmount());
        creditDTO.setMonthlyPayment(monthlyPayment);
        creditDTO.setRate(rate);
        creditDTO.setPsk(psk);

        assertEquals(creditDTO.getMonthlyPayment(),new BigDecimal(25045.645456700000));
        assertEquals(creditDTO.getAmount(),new BigDecimal(100000));
        assertEquals(creditDTO.getRate(),new BigDecimal(0.08));
        assertEquals(creditDTO.getPsk(),new BigDecimal(0E+54));

    }
}