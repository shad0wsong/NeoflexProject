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
        BigDecimal rate= BigDecimal.valueOf(8);
        if(scoringDataDTO.getEmployment().getEmploymentStatus()==UNEMPLOYED){
            log.info("DECLINED :UNEMPLOYED");
            return null;
        }
        if(scoringDataDTO.getEmployment().getEmploymentStatus()==SELFEMPLOYED){
            rate.add(BigDecimal.valueOf(1));
        }
        if(scoringDataDTO.getEmployment().getEmploymentStatus()==BUSINESSOWNER){
            rate.add(BigDecimal.valueOf(3));
        }

        if(scoringDataDTO.getEmployment().getPosition()==MIDDLE_MANAGER){
            rate.subtract(BigDecimal.valueOf(2));
        }
        if(scoringDataDTO.getEmployment().getPosition()==TOP_MANAGER){
            rate.subtract(BigDecimal.valueOf(4));
        }
        if(Integer.valueOf(String.valueOf(scoringDataDTO.getAmount()))>Integer.valueOf(String.valueOf(scoringDataDTO.getEmployment().getSalary()))*20){
            log.info("DECLINED :NOT ENOUGH SALARY");
            return null;
        }
        if(scoringDataDTO.getMaritalStatus()==MARRIED){
            rate.subtract(BigDecimal.valueOf(3));
        }
        if(scoringDataDTO.getMaritalStatus()==DIVORCED){
            rate.add(BigDecimal.valueOf(1));
        }
        if(scoringDataDTO.getDependentAmount()>1){
            rate.add(BigDecimal.valueOf(1));
        }
        if(LocalDate.now().getYear()-scoringDataDTO.getBirthday().getYear()<20 || LocalDate.now().getYear()-scoringDataDTO.getBirthday().getYear()>60 ){
            log.info("DECLINED :TOO OLD OR TOO YOUNG");
            return  null;
        }
        if(scoringDataDTO.getGender()==FEMALE
                && LocalDate.now().getYear()-scoringDataDTO.getBirthday().getYear()>35
                && LocalDate.now().getYear()-scoringDataDTO.getBirthday().getYear()<60){
            rate.subtract(BigDecimal.valueOf(3));
        }
        if(scoringDataDTO.getGender()==MALE
                && LocalDate.now().getYear()-scoringDataDTO.getBirthday().getYear()>30
                && LocalDate.now().getYear()-scoringDataDTO.getBirthday().getYear()<55){
            rate.subtract(BigDecimal.valueOf(3));
        }
        if(scoringDataDTO.getGender()==NON_BINARY){
            rate.add(BigDecimal.valueOf(3));
        }
        if(scoringDataDTO.getEmployment().getWorkExperienceTotal()<12){
            log.info("DECLINED :NOT ENOUGH WORK EXP");
            return null;
        }
        if(scoringDataDTO.getEmployment().getWorkExperienceCurrent()<3){
            log.info("DECLINED :NOT ENOUGH WORK EXP CURRENT");
            return null;
        }
        return rate;
    }

    @Override
    public BigDecimal calcPsk(ScoringDataDTO scoringDataDTO,BigDecimal rate) {
        Integer bp = 365/ scoringDataDTO.getTerm();
        Integer chbp =12;
        BigDecimal psk=BigDecimal.valueOf(chbp*0.01*100);// нашёл как считать пск тут но мне не хватает данных помогите https://habr.com/ru/post/233987/
        return psk;
    }

    @Override
    public BigDecimal calcMonthly(ScoringDataDTO scoringDataDTO,BigDecimal rate,Integer term) {// у нас аннуитентный платеж или дифференц?
        BigDecimal monthlyPayment=scoringDataDTO.getAmount().multiply((rate.divide(rate.add(BigDecimal.valueOf(1)), RoundingMode.HALF_UP)).subtract(BigDecimal.valueOf(term).subtract(BigDecimal.valueOf(1))) );
        return monthlyPayment;
    }
}
