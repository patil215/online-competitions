import ebcdic

def encodelist(encodelists):
  for enc in encodelists:
    print enc + " ", 'SHOW LIST IRRELEVANT'.encode(enc)

encodelist(["cp1140", "cp1141", "cp1142", "cp1143", "cp1144", "cp1145", "cp1146", "cp1147", "cp1148", "cp1149", "cp037", "cp273", "cp277", "cp278", "cp280", "cp284", "cp285", "cp297", "cp500", "cp871", "cp1047"])
