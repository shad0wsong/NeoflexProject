package conveyormc.controllers;

import conveyormc.exceptions.LoanApplicationRequestDTOValidationExc;
import conveyormc.dto.*;
import conveyormc.logicinterfaces.CalculationCreditInt;
import conveyormc.logicinterfaces.CreditDTOInt;
import conveyormc.logicinterfaces.OffersInt;
import conveyormc.logicinterfaces.PaymentScheduleInt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;


import java.util.List;
import java.util.logging.Logger;


@RestController
@Validated
@Api(value = "ConveyorController", description = "CreditCalculations")
public class ConveyorController {
    static Logger log = Logger.getLogger(ConveyorController.class.getName());

    @Autowired
    CalculationCreditInt calculationCredit;

    @Autowired
    OffersInt offersInt;

    @Autowired
    PaymentScheduleInt paymentScheduleInt;

    @Autowired
    CreditDTOInt creditDTOInt;

    @ApiOperation(value = "get offers", response = List.class)
    @PostMapping(value = "/conveyor/offers")
    public List<LoanOfferDTO> offers( @Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {

        log.info("Got LoanApplicationRequestDTO " + loanApplicationRequestDTO.getFirstName() + " " + loanApplicationRequestDTO.getLastName()
                + " " + loanApplicationRequestDTO.getMiddleName() + "amount: " + loanApplicationRequestDTO.getAmount() + " passport: " + loanApplicationRequestDTO.getPassportSeries() + " " +
                loanApplicationRequestDTO.getPassportNumber() + "birthday:" + loanApplicationRequestDTO.getBirthdayDate());

        List<LoanOfferDTO> offers = offersInt.getOffers(loanApplicationRequestDTO);

        log.info("Sended LoanOfferDTO " + offers);
        return offers;
    }

    @ApiOperation(value = "get creditDTO", response = CreditDTO.class)
    @PostMapping("/conveyor/calculation")
    public CreditDTO calculation(@RequestBody ScoringDataDTO scoringDataDTO) throws LoanApplicationRequestDTOValidationExc {
        log.info("Got ScoringDataDTO" + scoringDataDTO.getFirstName() + " " + scoringDataDTO.getLastName() + " amount:" + scoringDataDTO.getAmount() + " birthday :" + scoringDataDTO.getBirthday()
                + " term :" + scoringDataDTO.getTerm() + "dependent amount" + scoringDataDTO.getDependentAmount() + "gender :" + scoringDataDTO.getGender()
                + " marital status" + scoringDataDTO.getMaritalStatus());

        BigDecimal rate = calculationCredit.calcRate(scoringDataDTO);

        BigDecimal monthlyPayment = calculationCredit.calcMonthly(scoringDataDTO, rate, scoringDataDTO.getTerm());

        BigDecimal psk = calculationCredit.calcPsk(scoringDataDTO, monthlyPayment, scoringDataDTO.getTerm());

        List<PaymentScheduleElement> paymentScheduleElements = paymentScheduleInt.getPaymentSchedule(scoringDataDTO, monthlyPayment);

        CreditDTO creditDTO = creditDTOInt.setCreditInfo(scoringDataDTO, monthlyPayment, rate, psk, paymentScheduleElements);

        log.info("Sended CreditDTO" + " rate :" + creditDTO.getRate() + " psk :" + creditDTO.getPsk() + " amount: " + creditDTO.getAmount() + "montly payment:" + creditDTO.getMonthlyPayment());
        return creditDTO;
    }
}
