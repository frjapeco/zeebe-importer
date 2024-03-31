package fjpc.zeebe.zeebeimporter.amqp.dto;

public class Resource {

    private String resource;
    private String resourceName;

    public Resource() {
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

}
