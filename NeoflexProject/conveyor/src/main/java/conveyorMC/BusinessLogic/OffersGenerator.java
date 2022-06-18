package conveyormc.businesslogic;

import conveyormc.dto.LoanApplicationRequestDTO;
import conveyormc.dto.LoanOfferDTO;
import conveyormc.logicinterfaces.OffersInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OffersGenerator implements OffersInt {

    @Value("${rate}")
    BigDecimal rate;

    @Autowired
    CalculationCredit calculationCredit;

    public List<LoanOfferDTO> getOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
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
        return offers;
    }
}
