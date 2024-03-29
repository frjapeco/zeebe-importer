package fjpc.zeebe.zeebeimporter.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProcessInstance {

    @Id
    private Long id;
    private Long processId;

    public ProcessInstance() {
    }

    public ProcessInstance(Long id, Long processId) {
        this.id = id;
        this.processId = processId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getProcessId() {
        return processId;
    }

    public void setProcessId(long processId) {
        this.processId = processId;
    }

}
