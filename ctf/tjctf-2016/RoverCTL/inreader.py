file = open('instru.txt', 'r')

lines = []
for line in file:
  lines.append(line)

for shift in range(0, 16):
  pos = 0.0
  out = ""
  length = 0
  for line in lines:
    length += 1
    direction = line.split()[0]
    amount = int(line.split()[1], 16)
    multiplier = 1
    if not direction == "00":
      multiplier = -1
    pos = pos + (amount * multiplier)
  
    card = pos / 21.1764706 # 21.17 is 360 / 17, evenly dividing the number of hexadecimal cards
  
    letter = hex((int(round(card) + shift) % 16))
    print letter
    out += letter[2:]
  print out.decode('hex')




"""output = open('readable.txt', 'w')
for line in file:
  direction = line.split()[0]
  amount = line.split()[1]
  readable = ""
  if direction == "00":
    readable = "clock"
  else:
    readable = "count"
  output.write(readable + " " + str(int(amount, 16)) + "\n")

output.close()"""
