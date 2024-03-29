package fjpc.zeebe.zeebeimporter.respository;

import fjpc.zeebe.zeebeimporter.domain.ProcessInstance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessInstanceRepository extends CrudRepository<ProcessInstance, Long> {
}
