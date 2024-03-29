package fjpc.zeebe.zeebeimporter.mq.dto;

public class ProcessInstanceMessage {

    private int version;
    private long processInstanceKey;
    private long processDefinitionKey;
    private String bpmnProcessId;
    private Object variables;
    private Object[] startInstructions;

    public ProcessInstanceMessage() {
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public String getBpmnProcessId() {
        return bpmnProcessId;
    }

    public void setBpmnProcessId(String bpmnProcessId) {
        this.bpmnProcessId = bpmnProcessId;
    }

    public Object getVariables() {
        return variables;
    }

    public void setVariables(Object variables) {
        this.variables = variables;
    }

    public Object[] getStartInstructions() {
        return startInstructions;
    }

    public void setStartInstructions(Object[] startInstructions) {
        this.startInstructions = startInstructions;
    }

}
