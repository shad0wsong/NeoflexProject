package conveyormc.businesslogic;

import conveyormc.dto.CreditDTO;
import conveyormc.dto.PaymentScheduleElement;
import conveyormc.dto.ScoringDataDTO;
import conveyormc.logicinterfaces.CreditDTOInt;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreditDTOGenerator implements CreditDTOInt {
    public CreditDTO setCreditInfo(ScoringDataDTO scoringDataDTO, BigDecimal monthlyPayment, BigDecimal rate, BigDecimal psk, List<PaymentScheduleElement> paymentScheduleElements) {
        CreditDTO creditDTO = new CreditDTO();
        creditDTO.setAmount(scoringDataDTO.getAmount());
        creditDTO.setTerm(scoringDataDTO.getTerm());
        creditDTO.setMonthlyPayment(monthlyPayment);
        creditDTO.setRate(rate);
        creditDTO.setPsk(psk);
        creditDTO.setIsInsuranceEnabled(scoringDataDTO.getIsInsuranceEnabled());
        creditDTO.setIsSalaryClient(scoringDataDTO.getIsSalaryClient());
        creditDTO.setPaymentSchedule(paymentScheduleElements);
        return creditDTO;
    }
}
