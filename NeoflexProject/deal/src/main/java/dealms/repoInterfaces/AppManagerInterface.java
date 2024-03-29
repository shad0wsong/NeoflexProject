package dealms.repoInterfaces;

import dealms.models.Application;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AppManagerInterface extends CrudRepository<Application, Long> {

}
