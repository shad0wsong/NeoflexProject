package conveyormc.controllers;

import conveyormc.businesslogic.CalculationCredit;
import conveyormc.dto.*;
import conveyormc.exceptions.LoanApplicationRequestDTOValidationExc;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static conveyormc.enums.EmploymentPosition.TOP_MANAGER;
import static conveyormc.enums.EmploymentStatus.EMPLOYED;
import static conveyormc.enums.Gender.MALE;
import static conveyormc.enums.MaritalStatus.MARRIED;
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
        NumberFormat formatter = new DecimalFormat("#0.00");
        assertEquals(Optional.of(5),Optional.ofNullable(actual.get(0).getTerm()));
        formatter.format(Optional.ofNullable(actual.get(0).getRate()).get());
        assertEquals("0,07",formatter.format(Optional.ofNullable(actual.get(0).getRate()).get()));
        assertEquals(formatter.format(24389.069444137407),formatter.format(Optional.ofNullable(actual.get(0).getMonthlyPayment()).get()));
    }

    @Test
    public void calculation() throws LoanApplicationRequestDTOValidationExc {
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

        NumberFormat formatter = new DecimalFormat("#0.00");

        assertEquals(formatter.format(creditDTO.getMonthlyPayment()),formatter.format(25045.645456700000));
        assertEquals(creditDTO.getAmount(),new BigDecimal(100000));
        assertEquals("0,08",formatter.format(0.08));
        assertEquals(formatter.format(creditDTO.getPsk()),"0,00");

    }
}