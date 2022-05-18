package conveyorMC.dto;


import conveyorMC.LogicInterfaces.Validate;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanApplicationRequestDTO implements Validate {
    BigDecimal amount;
    Integer term;
    String firstName;
    String lastName;
    String middleName;
    String email;
    LocalDate birthdayDate;
    String passportSeries;
    String passportNumber;


    @Override
    public List<String> validate() {
        List<String> errorList = new ArrayList<>();
        Pattern p = Pattern.compile("[\\w\\.]{2,50}@[\\w\\.]{2,20}");
        Matcher matcher=p.matcher(email);
        if(firstName.length()<2 ||firstName.length()>30){
            errorList.add("Имя должно быть от 2 до 30 латинских букв.");
        }
        if(lastName.length()<2 ||lastName.length()>30){
            errorList.add("Фамилия должна быть от 2 до 30 латинских букв.");
        }
        if(middleName.length()<2 ||middleName.length()>30){
            errorList.add("Отчество должно быть от 2 до 30 латинских букв.");
        }
        if(Integer.valueOf(String.valueOf(amount))<10000){
            errorList.add("Сумма кредита должно быть действительно число, большее или равное 10000.");
        }
        if(LocalDate.now().getYear()-birthdayDate.getYear()<18){
            errorList.add("Дата рождения должно быть число в формате гггг-мм-дд, не позднее 18 лет с текущего дня.");
        }
        if(matcher==null){
            errorList.add("Email адрес - строка, подходящая под паттерн [\\w\\.]{2,50}@[\\w\\.]{2,20}");
        }
        if(passportSeries.length()!=4){
            errorList.add("Серия паспорта - 4 цифры");
        }
        if(passportSeries.length()!=6){
            errorList.add("Номер паспорта - 6 цифр.");
        }
        return errorList;
    }
}
