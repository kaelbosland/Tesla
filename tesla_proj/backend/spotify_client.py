import requests
import urllib.request

client_id="a95f4d68b9b94ccda85624e8b63494ad"
redirect_url="http://localhost:5000"
scope="user-read-private"
spotify_base_url = "https://accounts.spotify.com/authorize"

def construct_login_url(client_id, redirect_url, scope, spotify_base_url):
    return spotify_base_url + "?client_id=" + client_id + "&redirect_uri=" + redirect_url + "&scope=" + scope + "&response_type=token"

url = (construct_login_url(client_id, redirect_url, scope, spotify_base_url))
data = requests.get(url)

print (data)
