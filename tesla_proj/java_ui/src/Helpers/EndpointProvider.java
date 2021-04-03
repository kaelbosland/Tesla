package Helpers;

public class EndpointProvider {
    private String base;

    public EndpointProvider() {
        this.base = "http://localhost:5000";
    }

    public String getDevicesEndpoint() {
        return this.base + "/devices";
    }

    public String getCurrentPlaybackEndpoint() { return this.base + "/current_playback"; }

    public String getPlaylistByNameEndpoint(String name) { return this.base + "/playlist/" + name; }

    public String getAllPlaylistsEndpoint() { return this.base + "/json_playlist"; }

    public String getStartTrackPlaybackEndpoint(String source) { return this.base + "/playtrack/" + source; }

    public String getQueueTrackEndpoint(String source) { return this.base + "/queueup/" + source; }

    public String getBackEndpoint() { return this.base + "/back"; }

    public String getNextEndpoint() { return this.base + "/next"; }
}
