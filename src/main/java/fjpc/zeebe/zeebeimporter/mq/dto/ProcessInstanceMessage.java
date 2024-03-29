    package fjpc.zeebe.zeebeimporter.mq.dto;

public class ProcessInstanceMessage {

    private int version;
    private String bpmnProcessId;
    private long processInstanceKey;
    private long processDefinitionKey;
    private String elementId;
    private long flowScopeKey;
    private String bpmnElementType;
    private String bpmnEventType;
    private long parentProcessInstanceKey;
    private long parentElementInstanceKey;

    public ProcessInstanceMessage() {
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getBpmnProcessId() {
        return bpmnProcessId;
    }

    public void setBpmnProcessId(String bpmnProcessId) {
        this.bpmnProcessId = bpmnProcessId;
    }

    public long getProcessInstanceKey() {
        return processInstanceKey;
    }

    public void setProcessInstanceKey(long processInstanceKey) {
        this.processInstanceKey = processInstanceKey;
    }

    public long getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(long processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public long getFlowScopeKey() {
        return flowScopeKey;
    }

    public void setFlowScopeKey(long flowScopeKey) {
        this.flowScopeKey = flowScopeKey;
    }

    public String getBpmnElementType() {
        return bpmnElementType;
    }

    public void setBpmnElementType(String bpmnElementType) {
        this.bpmnElementType = bpmnElementType;
    }

    public String getBpmnEventType() {
        return bpmnEventType;
    }

    public void setBpmnEventType(String bpmnElementType) {
        this.bpmnEventType = bpmnElementType;
    }

    public long getParentProcessInstanceKey() {
        return parentProcessInstanceKey;
    }

    public void setParentProcessInstanceKey(long parentProcessInstanceKey) {
        this.parentProcessInstanceKey = parentProcessInstanceKey;
    }

    public long getParentElementInstanceKey() {
        return parentElementInstanceKey;
    }

    public void setParentElementInstanceKey(long parentElementInstanceKey) {
        this.parentElementInstanceKey = parentElementInstanceKey;
    }

}
