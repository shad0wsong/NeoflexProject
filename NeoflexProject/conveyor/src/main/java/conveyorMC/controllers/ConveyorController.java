package conveyorMC.controllers;


import conveyorMC.BusinessLogic.CalculationCredit;
import conveyorMC.Exceptions.LoanApplicationRequestDTOValidationExc;
import conveyorMC.JsonDeserializer.Deserialz;
import conveyorMC.dto.*;
import conveyorMC.enums.Gender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@Api(value="ConveyorController",description = "CreditCalculations")
public class ConveyorController {
    static Logger log = Logger.getLogger(ConveyorController.class.getName());

    @Value("8")
    String rate;

    @Autowired
    CalculationCredit calculationCredit;


    @ApiOperation(value = "get offers",response = List.class)
    @PostMapping(value = "/conveyor/offers" )
    public List<LoanOfferDTO> offers(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) throws LoanApplicationRequestDTOValidationExc {
        log.info("Got LoanApplicationRequestDTO ");


        List<String>errorList=loanApplicationRequestDTO.validate();
        if(!errorList.isEmpty()) {
            String errorString = "";
            for (String e : errorList) {
                errorString += e;
            }
            log.info("Bad request.ERROR 400 ");
            throw new LoanApplicationRequestDTOValidationExc(errorString);
        }


        List<LoanOfferDTO> offers = new ArrayList<>();
        offers.add(new LoanOfferDTO(null, loanApplicationRequestDTO.getAmount().add(BigDecimal.valueOf(1000)),
                loanApplicationRequestDTO.getTerm(), loanApplicationRequestDTO.getAmount().add(BigDecimal.valueOf(1000)).divide(BigDecimal.valueOf(loanApplicationRequestDTO.getTerm()),RoundingMode.HALF_UP),
                BigDecimal.valueOf(Integer.valueOf(rate)).subtract(BigDecimal.valueOf(1)), true, false));

        offers.add(new LoanOfferDTO(null, loanApplicationRequestDTO.getAmount().subtract(BigDecimal.valueOf(1000)),
                loanApplicationRequestDTO.getTerm(), loanApplicationRequestDTO.getAmount().add(BigDecimal.valueOf(1000)).divide(BigDecimal.valueOf(loanApplicationRequestDTO.getTerm()),RoundingMode.HALF_UP),
                BigDecimal.valueOf(Integer.valueOf(rate)).add(BigDecimal.valueOf(1)), false, false));

        offers.add(new LoanOfferDTO(null, loanApplicationRequestDTO.getAmount().subtract(BigDecimal.valueOf(1000)),
                loanApplicationRequestDTO.getTerm(), (loanApplicationRequestDTO.getAmount().add(BigDecimal.valueOf(1000))).divide(BigDecimal.valueOf(loanApplicationRequestDTO.getTerm()), RoundingMode.HALF_UP),
                BigDecimal.valueOf(Integer.valueOf(rate)).add(BigDecimal.valueOf(1)), false, true));

        offers.add(new LoanOfferDTO(null, loanApplicationRequestDTO.getAmount().add(BigDecimal.valueOf(1000)),
                loanApplicationRequestDTO.getTerm(), loanApplicationRequestDTO.getAmount().add(BigDecimal.valueOf(1000)).divide(BigDecimal.valueOf(loanApplicationRequestDTO.getTerm()),RoundingMode.HALF_UP),
                BigDecimal.valueOf(Integer.valueOf(rate)).subtract(BigDecimal.valueOf(1)), true, true));

        log.info("Sended LoanOfferDTO ");
        return offers;
    }

    @ApiOperation(value = "get creditDTO",response = CreditDTO.class)
    @PostMapping("/conveyor/calculation")
    public CreditDTO calculation(@RequestBody ScoringDataDTO scoringDataDTO) {
        log.info("Got ScoringDataDTO");
        CreditDTO creditDTO = new CreditDTO();
        BigDecimal rate = calculationCredit.calcRate(scoringDataDTO);

        BigDecimal psk = calculationCredit.calcPsk(scoringDataDTO, calculationCredit.calcRate(scoringDataDTO));

        BigDecimal monthlyPayment = calculationCredit.calcMonthly(scoringDataDTO, rate,scoringDataDTO.getTerm());

        List<PaymentScheduleElement> paymentScheduleElements = new ArrayList<>();
        paymentScheduleElements.add(new PaymentScheduleElement(0, LocalDate.now(), psk, monthlyPayment, monthlyPayment, psk.subtract(monthlyPayment)));
        for (int i = 1; i < scoringDataDTO.getTerm(); i++) {
            paymentScheduleElements.add(new PaymentScheduleElement(i, paymentScheduleElements.get(i - 1).
                    getDate().plusMonths(1), scoringDataDTO.getAmount(), monthlyPayment,
                    monthlyPayment,scoringDataDTO.getAmount().subtract(monthlyPayment.multiply(BigDecimal.valueOf(i-1)))));
        }

        creditDTO.setAmount(scoringDataDTO.getAmount());
        creditDTO.setTerm(scoringDataDTO.getTerm());
        creditDTO.setMonthlyPayment(monthlyPayment);
        creditDTO.setRate(rate);
        creditDTO.setPsk(psk);
        creditDTO.setIsInsuranceEnabled(scoringDataDTO.getIsInsuranceEnabled());
        creditDTO.setIsSalaryClient(scoringDataDTO.getIsSalaryClient());
        creditDTO.setPaymentSchedule(paymentScheduleElements);
        log.info("Sended CreditDTO");
        return creditDTO;
    }
}
