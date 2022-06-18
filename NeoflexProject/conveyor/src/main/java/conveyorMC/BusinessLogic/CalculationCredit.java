package conveyormc.businesslogic;

import conveyormc.exceptions.LoanApplicationRequestDTOValidationExc;
import conveyormc.logicinterfaces.CalculationCreditInt;
import conveyormc.dto.ScoringDataDTO;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.logging.Logger;

import static conveyormc.enums.EmploymentPosition.MIDDLE_MANAGER;
import static conveyormc.enums.EmploymentPosition.TOP_MANAGER;
import static conveyormc.enums.EmploymentStatus.*;
import static conveyormc.enums.Gender.*;
import static conveyormc.enums.MaritalStatus.*;

@Service
public class CalculationCredit implements CalculationCreditInt {

    static Logger log = Logger.getLogger(CalculationCredit.class.getName());

    @Override
    public BigDecimal calcRate(ScoringDataDTO scoringDataDTO) throws LoanApplicationRequestDTOValidationExc {
        BigDecimal rate = BigDecimal.valueOf(0.08);
        if (scoringDataDTO.getEmployment().getEmploymentStatus() == UNEMPLOYED) {
            log.info("DECLINED :UNEMPLOYED");
            throw new LoanApplicationRequestDTOValidationExc("DECLINED :UNEMPLOYED");
        }
        if (scoringDataDTO.getEmployment().getEmploymentStatus() == SELFEMPLOYED) {
            rate.add(BigDecimal.valueOf(0.01));
        }
        if (scoringDataDTO.getEmployment().getEmploymentStatus() == BUSINESSOWNER) {
            rate.add(BigDecimal.valueOf(0.03));
        }

        if (scoringDataDTO.getEmployment().getPosition() == MIDDLE_MANAGER) {
            rate.subtract(BigDecimal.valueOf(0.02));
        }
        if (scoringDataDTO.getEmployment().getPosition() == TOP_MANAGER) {
            rate.subtract(BigDecimal.valueOf(0.04));
        }
        if (Integer.valueOf(String.valueOf(scoringDataDTO.getAmount())) > Integer.valueOf(String.valueOf(scoringDataDTO.getEmployment().getSalary())) * 20) {
            log.info("DECLINED :NOT ENOUGH SALARY");
            throw new LoanApplicationRequestDTOValidationExc("DECLINED :NOT ENOUGH SALARY");
        }
        if (scoringDataDTO.getMaritalStatus() == MARRIED) {
            rate.subtract(BigDecimal.valueOf(0.03));
        }
        if (scoringDataDTO.getMaritalStatus() == DIVORCED) {
            rate.add(BigDecimal.valueOf(0.01));
        }
        if (scoringDataDTO.getDependentAmount() > 1) {
            rate.add(BigDecimal.valueOf(0.01));
        }

        if (LocalDate.now().getYear() - scoringDataDTO.getBirthday().getYear() < 20 || LocalDate.now().getYear() - scoringDataDTO.getBirthday().getYear() > 60) {
            log.info("DECLINED :TOO OLD OR TOO YOUNG");
            throw new LoanApplicationRequestDTOValidationExc("DECLINED :TOO OLD OR TOO YOUNG");
        }


        if (scoringDataDTO.getGender() == FEMALE
                && LocalDate.now().getYear() - scoringDataDTO.getBirthday().getYear() > 35
                && LocalDate.now().getYear() - scoringDataDTO.getBirthday().getYear() < 60) {
            rate.subtract(BigDecimal.valueOf(0.03));
        }
        if (scoringDataDTO.getGender() == MALE
                && LocalDate.now().getYear() - scoringDataDTO.getBirthday().getYear() > 30
                && LocalDate.now().getYear() - scoringDataDTO.getBirthday().getYear() < 55) {
            rate.subtract(BigDecimal.valueOf(0.03));
        }
        if (scoringDataDTO.getGender() == NON_BINARY) {
            rate.add(BigDecimal.valueOf(0.03));
        }
        if (scoringDataDTO.getEmployment().getWorkExperienceTotal() < 12) {
            log.info("DECLINED :NOT ENOUGH WORK EXP");
            throw new LoanApplicationRequestDTOValidationExc("DECLINED :NOT ENOUGH WORK EXP");
        }
        if (scoringDataDTO.getEmployment().getWorkExperienceCurrent() < 3) {
            log.info("DECLINED :NOT ENOUGH WORK EXP CURRENT");
            throw new LoanApplicationRequestDTOValidationExc("DECLINED :NOT ENOUGH WORK EXP CURRENT");
        }

        return rate;
    }

    @Override
    public BigDecimal calcPsk(ScoringDataDTO scoringDataDTO, BigDecimal monthly, Integer term) { //использовал формулу вот отсюда https://temabiz.com/finterminy/psk-uproshhennyj-raschet.html

        BigDecimal creditSum = scoringDataDTO.getAmount();

        BigDecimal allCreditSum = monthly.multiply(BigDecimal.valueOf(term));

        double yearTerm = (double) term / 12;

        BigDecimal psk = ((creditSum.divide(allCreditSum, RoundingMode.HALF_UP)).subtract(new BigDecimal(1))).divide(new BigDecimal(yearTerm));

        return psk;
    }

    @Override
    public BigDecimal calcMonthly(ScoringDataDTO scoringDataDTO, BigDecimal rate, Integer term) {

        BigDecimal annuKoef = (rate.multiply((new BigDecimal(1).add(rate)).pow(term))).divide(((new BigDecimal(1).add(rate)).pow(term)).subtract(new BigDecimal(1)), RoundingMode.HALF_UP);

        BigDecimal monthlyPayment = scoringDataDTO.getAmount().multiply(annuKoef); //формула отсюда https://journal.tinkoff.ru/guide/credit-payment/

        return monthlyPayment;
    }

    @Override
    public BigDecimal calcMonthlyOffer(BigDecimal amount, BigDecimal rate, Integer term) {

        BigDecimal annuKoef = (rate.multiply((new BigDecimal(1).add(rate)).pow(term))).divide(((new BigDecimal(1).add(rate)).pow(term)).subtract(new BigDecimal(1)), RoundingMode.HALF_UP);

        BigDecimal monthlyPayment = amount.multiply(annuKoef); //формула отсюда https://journal.tinkoff.ru/guide/credit-payment/

        return monthlyPayment;
    }

}
