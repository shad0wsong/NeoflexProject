package dealms.repoInterfaces;

import dealms.models.Credit;
import org.springframework.data.repository.CrudRepository;

public interface CreditManagerInterface extends CrudRepository<Credit, Long> {
}
