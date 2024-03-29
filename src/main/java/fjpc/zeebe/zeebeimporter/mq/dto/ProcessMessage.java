package fjpc.zeebe.zeebeimporter.mq.dto;

public class ProcessMessage {

    private Resource[] resources;
    private ProcessMetadata[] processesMetadata;
    private Object[] decisionsMetadata;
    private Object[] decisionRequirementsMetadata;

    public ProcessMessage() {
    }

    public Resource[] getResources() {
        return resources;
    }

    public void setResources(Resource[] resources) {
        this.resources = resources;
    }

    public ProcessMetadata[] getProcessesMetadata() {
        return processesMetadata;
    }

    public void setProcessesMetadata(ProcessMetadata[] processesMetadata) {
        this.processesMetadata = processesMetadata;
    }

    public Object[] getDecisionsMetadata() {
        return decisionsMetadata;
    }

    public void setDecisionsMetadata(Object[] decisionsMetadata) {
        this.decisionsMetadata = decisionsMetadata;
    }

    public Object[] getDecisionRequirementsMetadata() {
        return decisionRequirementsMetadata;
    }

    public void setDecisionRequirementsMetadata(Object[] decisionRequirementsMetadata) {
        this.decisionRequirementsMetadata = decisionRequirementsMetadata;
    }

}
