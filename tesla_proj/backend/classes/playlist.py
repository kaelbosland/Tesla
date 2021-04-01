class Playlist:
    def __init__(self, data):
        self.id = data["id"]
        self.source = data["uri"]
        self.name = data["name"]
        self.tracks = {}

    def set_tracks(self, tracks):
        self.tracks = tracks

    def get(self):
        return {
            "id": self.id,
            "source": self.source,
            "name": self.name,
            "tracks": self.tracks
        }

    def jsonify(self):
        return {
            "id": self.id,
            "source": self.source,
            "name": self.name,
            "tracks": self.jsonify_tracks()
        }


    def __hash__(self):
        return self.id

    def jsonify_tracks(self):
        jsonified_tracks = {}
        for track in self.tracks:
            jsonified_tracks[track] = self.tracks[track].get()
        return jsonified_tracks