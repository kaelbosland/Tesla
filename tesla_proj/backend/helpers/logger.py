from datetime import datetime

class Logger:
    def __init__(self, path):
        current = datetime.now()
        try:
            self.file = open(path + "/" + (str(current.year) + "-" + str(current.month) + "-" + str(current.day) + "T" + str(current.hour)), "a")
            self.log('\n\n------------------------ ' + str(current) + '---------------------------\n\n')
        except FileNotFoundError:
            self.file = open(path + "/" + (str(current.year) + "-" + str(current.month) + "-" + str(current.day) + "T" + str(current.hour)), "x")
        
    def log (self, input):
        current = datetime.now()
        self.file = open("logs" + "/" + (str(current.year) + "-" + str(current.month) + "-" + str(current.day) + "T" + str(current.hour)), "a")
        self.file.writelines(input)
        self.file.close()