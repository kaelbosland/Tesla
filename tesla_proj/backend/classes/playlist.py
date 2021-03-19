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

    def __hash__(self):
        return self.id