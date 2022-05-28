package dealMC.repoInterfaces;

import dealMC.models.Credit;
import org.springframework.data.repository.CrudRepository;

public interface CreditManagerInterface extends CrudRepository<Credit, Long> {
}
