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

}
