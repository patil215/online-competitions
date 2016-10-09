import time

import socket



def find_splits(chars, linelength):
	splits = []
	for i in range(0, linelength):
		all_empty = True
		for d in range(0, 6):
			if(not chars[d][i] == " "):
				all_empty = False
		if(all_empty):
			splits.append(i)
	return splits

def prettyprintletter(letter):
	for line in letter:
		thing = ''
		for char in line:
			thing += char
		print thing

def genletterstring(letter):
	letterstring = ''
	for line in letter:
		for char in line:
			if not char == '\n' and not char == ' ':
				letterstring += char
	return letterstring



file = open('letters', 'r')
filelines = file.readlines()
charstomap = "aAbBcCdDeEfFgGhHjJkKLmMnNPpqQrRstTuUvVwWyYzZ234679"
asciimaps = {}

for i in range(0, len(charstomap)):
	letter = []
	for d in range(0, 6):
		letter.append([])
		for k in filelines[i * 6 + d]:
			letter[d].append(k)
	char = charstomap[i]
	asciimaps[char] = genletterstring(letter)


def getletters(result):
	answers = []
	

	lines = result.split("\n")
	chars = []
	# Important stuff starts on line 3
	for i in range(3, len(lines) - 2):
		line = lines[i]
		chars.append([])
		for d in range(0, len(line)):
			chars[i - 3].append(line[d])

	linelength = len(chars[0])
	splits = find_splits(chars, linelength)

	letters = []

	for i in range(0, len(splits)):
		letters.append([])
		letter = letters[i]
		startX = splits[i] + 1
		if i == len(splits) - 1:
			endX = linelength
		else:
			endX = splits[i + 1]
		for d in range(0, 6):
			letter.append([])
			for k in range(startX, endX):
				letter[d].append(chars[d][k])


	#for letter in letters:
	#	prettyprintletter(letter)

	for letter in letters:
		for key, asciistring in asciimaps.iteritems():
			if asciistring == genletterstring(letter):
				answers.append(key)
	return answers

def netcat(hostname, port, content):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((hostname, port))
    s.sendall(content)
    letters = []
    for i in range(0, 102):
	    rec_data = []
	    while 1:
	        data = s.recv(1024)
	        if data == "":
	            break
	        rec_data.append(data)
	        print rec_data[0]
	        break;
	    letters = getletters(rec_data[0])
	    print letters
	    doop = ''
	    for char in letters:
	    	doop += char
	    doop += "\n"
	    s.sendall(doop)


netcat("p.tjctf.org", 8008, "")[0]