package project.io.app.core.common.request;

public class HealthCheckRequest {

    private Long serverId;

    private HealthCheckRequest() {
    }

    public HealthCheckRequest(final Long serverId) {
        this.serverId = serverId;
    }

    public Long getServerId() {
        return serverId;
    }
}
