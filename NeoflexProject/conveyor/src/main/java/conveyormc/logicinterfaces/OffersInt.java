package conveyormc.logicinterfaces;

import conveyormc.dto.LoanApplicationRequestDTO;
import conveyormc.dto.LoanOfferDTO;

import java.util.List;

public interface OffersInt {
    List<LoanOfferDTO> getOffers(LoanApplicationRequestDTO loanApplicationRequestDTO);
}
