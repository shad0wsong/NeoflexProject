package conveyorMC.BusinessLogic;

import conveyorMC.LogicInterfaces.CalculationCreditInt;
import conveyorMC.controllers.ConveyorController;
import conveyorMC.dto.ScoringDataDTO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static conveyorMC.enums.EmploymentPosition.MIDDLE_MANAGER;
import static conveyorMC.enums.EmploymentPosition.TOP_MANAGER;
import static conveyorMC.enums.EmploymentStatus.*;
import static conveyorMC.enums.Gender.*;
import static conveyorMC.enums.MaritalStatus.*;

@Service
public class CalculationCredit implements CalculationCreditInt {

    static Logger log = Logger.getLogger(CalculationCredit.class.getName());
    @Override
    public BigDecimal calcRate(ScoringDataDTO scoringDataDTO) {
        BigDecimal rate= BigDecimal.valueOf(0.08);
        if(scoringDataDTO.getEmployment().getEmploymentStatus()==UNEMPLOYED){
            log.info("DECLINED :UNEMPLOYED");
            return null;
        }
        if(scoringDataDTO.getEmployment().getEmploymentStatus()==SELFEMPLOYED){
            rate.add(BigDecimal.valueOf(0.01));
        }
        if(scoringDataDTO.getEmployment().getEmploymentStatus()==BUSINESSOWNER){
            rate.add(BigDecimal.valueOf(0.03));
        }

        if(scoringDataDTO.getEmployment().getPosition()==MIDDLE_MANAGER){
            rate.subtract(BigDecimal.valueOf(0.02));
        }
        if(scoringDataDTO.getEmployment().getPosition()==TOP_MANAGER){
            rate.subtract(BigDecimal.valueOf(0.04));
        }
        if(Integer.valueOf(String.valueOf(scoringDataDTO.getAmount()))>Integer.valueOf(String.valueOf(scoringDataDTO.getEmployment().getSalary()))*20){
            log.info("DECLINED :NOT ENOUGH SALARY");
            return null;
        }
        if(scoringDataDTO.getMaritalStatus()==MARRIED){
            rate.subtract(BigDecimal.valueOf(0.03));
        }
        if(scoringDataDTO.getMaritalStatus()==DIVORCED){
            rate.add(BigDecimal.valueOf(0.01));
        }
        if(scoringDataDTO.getDependentAmount()>1){
            rate.add(BigDecimal.valueOf(0.01));
        }
        if(LocalDate.now().getYear()-scoringDataDTO.getBirthday().getYear()<20 || LocalDate.now().getYear()-scoringDataDTO.getBirthday().getYear()>60 ){
            log.info("DECLINED :TOO OLD OR TOO YOUNG");
            return  null;
        }
        if(scoringDataDTO.getGender()==FEMALE
                && LocalDate.now().getYear()-scoringDataDTO.getBirthday().getYear()>35
                && LocalDate.now().getYear()-scoringDataDTO.getBirthday().getYear()<60){
            rate.subtract(BigDecimal.valueOf(0.03));
        }
        if(scoringDataDTO.getGender()==MALE
                && LocalDate.now().getYear()-scoringDataDTO.getBirthday().getYear()>30
                && LocalDate.now().getYear()-scoringDataDTO.getBirthday().getYear()<55){
            rate.subtract(BigDecimal.valueOf(0.03));
        }
        if(scoringDataDTO.getGender()==NON_BINARY){
            rate.add(BigDecimal.valueOf(0.03));
        }
        if(scoringDataDTO.getEmployment().getWorkExperienceTotal()<12){
            log.info("DECLINED :NOT ENOUGH WORK EXP");
            return null;
        }
        if(scoringDataDTO.getEmployment().getWorkExperienceCurrent()<3){
            log.info("DECLINED :NOT ENOUGH WORK EXP CURRENT");
            return null;
        }
        System.out.println("Rate:"+rate);
        return rate;
    }

    @Override
    public BigDecimal calcPsk(ScoringDataDTO scoringDataDTO,BigDecimal monthly,Integer term) { //использовал формулу вот отсюда https://temabiz.com/finterminy/psk-uproshhennyj-raschet.html

        BigDecimal creditSum=scoringDataDTO.getAmount();

        BigDecimal allCreditSum = monthly.multiply(BigDecimal.valueOf(term));

        double yearTerm = (double)term/12;

        BigDecimal psk = ((creditSum.divide(allCreditSum,RoundingMode.HALF_UP)).subtract(new BigDecimal(1))).divide(new BigDecimal(yearTerm));
        System.out.println("PSK:"+psk);
        return psk;
    }

    @Override
    public BigDecimal calcMonthly(ScoringDataDTO scoringDataDTO,BigDecimal rate,Integer term) {
        System.out.println( "Rate:"+rate);
        BigDecimal annuKoef=(rate.multiply( (new BigDecimal(1).add(rate)).pow(term))).divide(((new BigDecimal(1).add(rate)).pow(term)).subtract(new BigDecimal(1)),RoundingMode.HALF_UP);
        System.out.println("AnnuKoef:"+annuKoef);
        BigDecimal monthlyPayment=scoringDataDTO.getAmount().multiply(annuKoef); //формула отсюда https://journal.tinkoff.ru/guide/credit-payment/
        System.out.println("Monthly:"+monthlyPayment);
        return monthlyPayment;
    }

    @Override
    public BigDecimal calcMonthlyOffer(BigDecimal amount,BigDecimal rate,Integer term) {
        System.out.println( "Rate:"+rate);
        BigDecimal annuKoef=(rate.multiply( (new BigDecimal(1).add(rate)).pow(term))).divide(((new BigDecimal(1).add(rate)).pow(term)).subtract(new BigDecimal(1)),RoundingMode.HALF_UP);
        System.out.println("AnnuKoef:"+annuKoef);
        BigDecimal monthlyPayment=amount.multiply(annuKoef); //формула отсюда https://journal.tinkoff.ru/guide/credit-payment/
        System.out.println("Monthly:"+monthlyPayment);
        return monthlyPayment;
    }

}
