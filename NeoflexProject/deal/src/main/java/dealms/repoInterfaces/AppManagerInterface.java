package dealms.repoInterfaces;

import dealms.models.Application;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AppManagerInterface extends CrudRepository<Application, Long> {
    @Query("SELECT a FROM Application a WHERE a.UUID=:UUID")
    Application getIdByUUID(@Param("UUID") String UUID);
}
