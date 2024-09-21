package project.io.app.core.common.request;

public class ExcludeRequest {

    private Long serverId;

    private ExcludeRequest() {
    }

    public ExcludeRequest(final Long serverId) {
        this.serverId = serverId;
    }

    public Long getServerId() {
        return serverId;
    }
}
