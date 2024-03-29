package fjpc.zeebe.zeebeimporter.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

public class ProcessInstance implements Persistable<Long> {

    @Id
    private Long id;
    private Long processId;
    @Transient
    private boolean isNew = true;

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

    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

}
