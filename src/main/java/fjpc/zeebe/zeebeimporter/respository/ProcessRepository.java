package fjpc.zeebe.zeebeimporter.respository;

import fjpc.zeebe.zeebeimporter.domain.Process;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends CrudRepository<Process, Long> {
}
