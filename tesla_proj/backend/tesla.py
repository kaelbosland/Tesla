import random
from flask import (
    Flask,
    jsonify,
    url_for,
    redirect,
    make_response
)
import json
import os
import requests
import string

import spotipy
from spotipy.oauth2 import SpotifyOAuth
from pprint import pprint
from time import sleep

app = Flask(__name__)

DEVICE="e14fc50b1466fb4e9400f80c7ae34158a3e07f6a"
scope = 'user-read-playback-state,user-modify-playback-state,playlist-read-private'
SP = spotipy.Spotify(client_credentials_manager=SpotifyOAuth(scope=scope))

@app.route('/current_playback')
def playback():
    return SP.current_playback()

@app.route('/devices')
def devices():
    res = SP.devices()
    return(res)

@app.route('/play_playlist/<source>')
def play_playlist(source):
    try:
        SP.start_playback(DEVICE, get_playlist(source))
    except ConnectionError:
        return ("500")
    return ("200 OK")

@app.route('/next')
def next():
    try:
        SP.next_track(DEVICE)
    except ConnectionError:
        return ("500")
    return ("200 OK")

@app.route('/back')
def back():
    try:
        SP.previous_track(DEVICE)
    except ConnectionError:
        return ("500")
    return ("200 OK")

def get_playlist(name):
    try:
        return locate_playlist(SP.current_user_playlists()["items"], name)
    except KeyError:
        return ""

def locate_playlist(playlists, name):
    for i in range (0, len(playlists)):
        if ((playlists[i]['name']) == name):
            return playlists[i]["uri"]
    return {}