package Spotify;

import org.json.simple.JSONObject;

public class Device {
    private String id;
    private String name;
    private String volume;

    public Device(JSONObject deviceJson) {
        this.id = deviceJson.get("id").toString();
        this.name = deviceJson.get("name").toString();
        this.volume = deviceJson.get("volume_percent").toString();
    }

    @Override
    public String toString() {
        return "ID: " + this.id +
                "\nName: " + this.name +
                "\nVolume: " + this.volume;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
