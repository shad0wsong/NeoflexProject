package dealms.repoInterfaces;


import dealms.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientManagerInterface extends CrudRepository<Client, Long> {
}
