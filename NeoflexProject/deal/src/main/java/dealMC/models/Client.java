package dealMC.models;

import dealMC.enums.EmploymentPosition;
import dealMC.enums.EmploymentStatus;
import dealMC.enums.Gender;
import dealMC.enums.MaritalStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne(mappedBy = "client")
    Application application;

    String firstName;
    String lastName;
    String middleName;
    String email;
    LocalDate birthdayDate;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Enumerated(EnumType.STRING)
    MaritalStatus maritalStatus;
    @Enumerated(EnumType.STRING)
    EmploymentStatus employmentStatus;
    @Enumerated(EnumType.STRING)
    EmploymentPosition employmentPosition;
    Integer dependentAmount;
    String passportSeries;
    String passportNumber;
    LocalDate issue_date;
    String issue_branch;
    String employer;
    BigDecimal salary;
    Integer workExperienceTotal;
    Integer workExperienceCurrent;
    String account;

    public Client(Application application, String firstName, String lastName, String middleName, String email, LocalDate birthdayDate, Gender gender, MaritalStatus maritalStatus, EmploymentStatus employmentStatus, EmploymentPosition employmentPosition, Integer dependentAmount, String passportSeries, String passportNumber, LocalDate issue_date, String issue_branch, String employer, BigDecimal salary, Integer workExperienceTotal, Integer workExperienceCurrent, String account) {
        this.application = application;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.employmentStatus = employmentStatus;
        this.employmentPosition = employmentPosition;
        this.dependentAmount = dependentAmount;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.issue_date = issue_date;
        this.issue_branch = issue_branch;
        this.employer = employer;
        this.salary = salary;
        this.workExperienceTotal = workExperienceTotal;
        this.workExperienceCurrent = workExperienceCurrent;
        this.account = account;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", "+
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", gender=" + gender +
                ", maritalStatus=" + maritalStatus +
                ", employmentStatus=" + employmentStatus +
                ", employmentPosition=" + employmentPosition +
                ", dependentAmount=" + dependentAmount +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", issue_date=" + issue_date +
                ", issue_branch='" + issue_branch + '\'' +
                ", employer='" + employer + '\'' +
                ", salary=" + salary +
                ", workExperienceTotal=" + workExperienceTotal +
                ", workExperienceCurrent=" + workExperienceCurrent +
                ", account='" + account + '\'' +
                '}';
    }
}