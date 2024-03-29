package fjpc.zeebe.zeebeimporter.respository;

import fjpc.zeebe.zeebeimporter.domain.Process;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends R2dbcRepository<Process, Long> {
}
