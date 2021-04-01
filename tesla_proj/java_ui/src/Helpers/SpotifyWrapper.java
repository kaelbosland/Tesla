package Helpers;

import Spotify.Playlist;
import Spotify.Track;
import Spotify.Device;
import java.io.IOException;
import java.util.List;

public class SpotifyWrapper {

    private EndpointProvider endpointProvider;

    public SpotifyWrapper(EndpointProvider endpointProvider) {
        this.endpointProvider = endpointProvider;
    }

    private Object makeRequest(String endpoint) throws IOException {
        return RequestHelper.makeRequestGetResponse(endpoint);
    }

    public List<Device> getAllDevices() throws IOException {
        return JSONConverter.convertToDevicesList(
                this.makeRequest(endpointProvider.getDevicesEndpoint()).toString()
        );
    }

    public Track getCurrentPlayback() throws IOException {
        return JSONConverter.convertTrack(
                this.makeRequest(endpointProvider.getCurrentPlaybackEndpoint()).toString()
        );
    }

    public Playlist getPlaylist(String name) throws IOException {
        return JSONConverter.convertPlaylist(
                this.makeRequest(endpointProvider.getPlaylistByNameEndpoint(name)).toString()
        );
    }

    public void playPlaylist(String name) {

    }

    public void next() {

    }

    public void back() {

    }

    public void queueup() {

    }
}
