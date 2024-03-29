package fjpc.zeebe.zeebeimporter.respository;

import fjpc.zeebe.zeebeimporter.domain.ProcessInstance;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessInstanceRepository extends R2dbcRepository<ProcessInstance, Long> {
}
