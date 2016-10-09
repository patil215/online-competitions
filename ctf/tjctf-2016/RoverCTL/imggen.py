from PIL import Image

words = []

file = open("img", 'r')

for line in file:
  for word in line.split():
    words.append(word)


imgsize = (800, 600)
img = Image.new("RGB", imgsize)
pixels = img.load()

print len(words)

k = 0
for d in range(0, 800):
  for i in range(0, 600):
    val = int(words[k], 16)
    pixels[d,i] = (val, val, val)
    k += 1
img.save('output.jpg')
