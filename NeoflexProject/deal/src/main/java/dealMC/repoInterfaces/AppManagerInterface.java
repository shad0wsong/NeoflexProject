package dealMC.repoInterfaces;

import dealMC.models.Application;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppManagerInterface extends CrudRepository<Application, Long> {
    @Query("SELECT a FROM Application a WHERE a.UUID=:UUID")
    Optional<Application> getIdByUUID(@Param("UUID") String UUID);
}
