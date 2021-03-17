import random
from flask import Flask

app = Flask(__name__)

@app.route('/tesla')
def whip_a_tesla():
    return {'how many bitches can we fit in this tesla': random.randint(0,10)} 
