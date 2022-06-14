package conveyormc.controllers;


import conveyormc.businesslogic.CalculationCredit;
import conveyormc.exceptions.LoanApplicationRequestDTOValidationExc;
import conveyormc.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@RestController
@Api(value = "ConveyorController", description = "CreditCalculations")
public class ConveyorController {
    static Logger log = Logger.getLogger(ConveyorController.class.getName());

    @Value("${rate}")
    BigDecimal rate;

    @Autowired
    CalculationCredit calculationCredit;


    @ApiOperation(value = "get offers", response = List.class)
    @PostMapping(value = "/conveyor/offers")
    public List<LoanOfferDTO> offers(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) throws LoanApplicationRequestDTOValidationExc {

        log.info("Got LoanApplicationRequestDTO " + loanApplicationRequestDTO.getFirstName() + " " + loanApplicationRequestDTO.getLastName()
                + " " + loanApplicationRequestDTO.getMiddleName() + "amount: " + loanApplicationRequestDTO.getAmount() + " passport: " + loanApplicationRequestDTO.getPassportSeries() + " " +
                loanApplicationRequestDTO.getPassportNumber() + "birthday:" + loanApplicationRequestDTO.getBirthdayDate());


        List<LoanOfferDTO> offers = new ArrayList<>();
        offers.add(new LoanOfferDTO(null, loanApplicationRequestDTO.getAmount(),
                loanApplicationRequestDTO.getTerm(), calculationCredit.calcMonthlyOffer(loanApplicationRequestDTO.getAmount(), rate.subtract(BigDecimal.valueOf(0.01)), loanApplicationRequestDTO.getTerm()),
                rate.subtract(BigDecimal.valueOf(0.01)), true, false));

        offers.add(new LoanOfferDTO(null, loanApplicationRequestDTO.getAmount(),
                loanApplicationRequestDTO.getTerm(), calculationCredit.calcMonthlyOffer(loanApplicationRequestDTO.getAmount(), rate.add(BigDecimal.valueOf(0.01)), loanApplicationRequestDTO.getTerm()),
                rate.add(BigDecimal.valueOf(0.01)), false, false));

        offers.add(new LoanOfferDTO(null, loanApplicationRequestDTO.getAmount(),
                loanApplicationRequestDTO.getTerm(), calculationCredit.calcMonthlyOffer(loanApplicationRequestDTO.getAmount(), rate.add(BigDecimal.valueOf(0.01)), loanApplicationRequestDTO.getTerm()),
                rate.add(BigDecimal.valueOf(0.01)), false, true));

        offers.add(new LoanOfferDTO(null, loanApplicationRequestDTO.getAmount(),
                loanApplicationRequestDTO.getTerm(), calculationCredit.calcMonthlyOffer(loanApplicationRequestDTO.getAmount(), rate.subtract(BigDecimal.valueOf(0.01)), loanApplicationRequestDTO.getTerm()),
                rate.subtract(BigDecimal.valueOf(0.01)), true, true));

        log.info("Sended LoanOfferDTO " + offers);
        return offers;
    }

    @ApiOperation(value = "get creditDTO", response = CreditDTO.class)
    @PostMapping("/conveyor/calculation")
    public CreditDTO calculation(@RequestBody ScoringDataDTO scoringDataDTO) {
        log.info("Got ScoringDataDTO" + scoringDataDTO.getFirstName() + " " + scoringDataDTO.getLastName() + " amount:" + scoringDataDTO.getAmount() + " birthday :" + scoringDataDTO.getBirthday()
                + " term :" + scoringDataDTO.getTerm() + "dependent amount" + scoringDataDTO.getDependentAmount() + "gender :" + scoringDataDTO.getGender()
                + " marital status" + scoringDataDTO.getMaritalStatus());
        CreditDTO creditDTO = new CreditDTO();
        BigDecimal rate = calculationCredit.calcRate(scoringDataDTO);

        BigDecimal monthlyPayment = calculationCredit.calcMonthly(scoringDataDTO, rate, scoringDataDTO.getTerm());

        BigDecimal psk = calculationCredit.calcPsk(scoringDataDTO, monthlyPayment, scoringDataDTO.getTerm());

        List<PaymentScheduleElement> paymentScheduleElements = new ArrayList<>();

        for (int i = 1; i <= scoringDataDTO.getTerm(); i++) {
            BigDecimal remainingDebt = scoringDataDTO.getAmount().subtract(monthlyPayment.multiply(BigDecimal.valueOf(i - 1)));
            if (remainingDebt.intValue() < 0) {
                remainingDebt = new BigDecimal(0);
            }
            paymentScheduleElements.add(new PaymentScheduleElement(i, LocalDate.now()
                    .plusMonths(i), scoringDataDTO.getAmount(), monthlyPayment,
                    monthlyPayment, remainingDebt));
        }

        creditDTO.setAmount(scoringDataDTO.getAmount());
        creditDTO.setTerm(scoringDataDTO.getTerm());
        creditDTO.setMonthlyPayment(monthlyPayment);
        creditDTO.setRate(rate);
        creditDTO.setPsk(psk);
        creditDTO.setIsInsuranceEnabled(scoringDataDTO.getIsInsuranceEnabled());
        creditDTO.setIsSalaryClient(scoringDataDTO.getIsSalaryClient());
        creditDTO.setPaymentSchedule(paymentScheduleElements);
        log.info("Sended CreditDTO" + " rate :" + creditDTO.getRate() + " psk :" + creditDTO.getPsk() + " amount: " + creditDTO.getAmount() + "montly payment:" + creditDTO.getMonthlyPayment());
        return creditDTO;
    }
}
