import socket
import time
import random
import calendar
import sys

def netcat(hostname, port, content):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((hostname, port))
    s.sendall(content)
    letters = []
    rec_data = []
    while 1:
        data = s.recv(1024)
        if data == "":
            break
        rec_data.append(data)
        break;
    s.sendall("1\n")
    response = s.recv(1024)
    wanted = int(response[response.index("was ") + 4:len(response) - 2])
    random.seed(int(time.time()))
    print "Time: ", int(time.time())
    print "Random: ", random.randint(0, int(time.time()))
    print "Wanted: ", wanted
    if(wanted != prevnum):
        prevnum = wanted
    print ""
    print ""
    s.close()




for i in range(0, 100):
	netcat("p.tjctf.org", 8007, "")