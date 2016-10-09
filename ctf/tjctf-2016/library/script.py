library = {}
libraryfile = open("library.txt", 'r')
listfile = open("input2.txt", 'r')

for line in libraryfile:
  comp = line.split("::")
  dewey = comp[0].strip()
  social = comp[1].strip()
  library[dewey] = social

filedata = ""
with open('input3.txt', 'r') as myfile:
  filedata=myfile.read().replace('\n', '')
print filedata
lines = filedata.split(" ... ")
del lines[-1]
print lines

tot = ""
for line in lines:
  comp = line.split(" ")
  join = ""
  for i in range(0, len(comp) - 1):
    join += comp[i] + " "
  join = join[0:len(join) - 1]
  dewey = join
  index = int(comp[-1])
  tot += library[dewey][index]

print tot

num_to_letter = {193: "A", 194: "B", 195: "C", 196: "D", 197: "E", 198: "F", 199: "G", 200: "H", 201: "I", 209: "J", 210: "K", 211: "L", 212: "M", 213: "N", 214: "O", 215: "P", 216: "Q", 217: "R", 226: "S", 227: "T", 228: "U", 229: "V", 230: "W", 231: "X", 232: "Y", 233: "Z", 64: " "}

totty = ""
ff = open("inner.txt", 'r')
for line in ff:
  totty += num_to_letter[line[0: len(line) - 1]]
print totty

print tot.decode('EBCDIC-CP-BE')
