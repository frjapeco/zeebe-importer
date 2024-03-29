package fjpc.zeebe.zeebeimporter.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Process {

    @Id
    private Long id;
    private String tag;
    private Integer versionNumber;

    public Process() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

}
