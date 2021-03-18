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
from urllib.parse import urlencode

app = Flask(__name__)

#Spotify information
CLIENT_ID=os.getenv('CLIENT_ID')
CLIENT_SECRET=os.getenv('CLIENT_SECRET')

#Spotify endpoints
AUTH_URL = 'https://accounts.spotify.com/authorize'
TOKEN_URL = 'https://accounts.spotify.com/api/token'
REDIRECT_URI = 'https://www.google.com'
ME_URL = 'http://api.spotify.com/v1/me'

@app.route('/login')
def login():
    state = '0101010101'

    scope = 'user-read-private user-read-email'

    payload = {
        'client_id': CLIENT_ID,
        'response_type': 'code',
        'redirect_uri': REDIRECT_URI,
        'state': state,
        'scope': scope
    }

    response = make_response(redirect(str(AUTH_URL) + '/?' + str({urlencode(payload)})))
    response.set_cookie('spotify_auth_state', state)

    return response

@app.route('/')
def home():
    return 'we home\n'

@app.route('/tesla')
def whip_a_tesla():
    return jsonify(['model 3', 'model s', 'model x', 'model y'])
