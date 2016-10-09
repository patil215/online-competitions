import sys

AVAILABLE = 0
PEG = 1
UNPLAYABLE = 2

def getValidMoves(board, pegLocations):
  locs = []

  for key in pegLocations:
    i = pegLocations[key][0]
    d = pegLocations[key][1]

    if i < len(board) - 2 and board[i][d] == PEG and board[i+1][d] == PEG and board[i + 2][d] == AVAILABLE:
      locs.append([[i, d],[i+1, d], [i+2, d]])
    if d < len(board[0]) - 2 and board[i][d] == PEG and board[i][d+ 1] == PEG and board[i][d + 2] == AVAILABLE:
      locs.append([[i, d], [i, d+1], [i, d+2]])
    if i > 2 and board[i][d] == PEG and board[i - 1][d] == PEG and board[i - 2][d] == AVAILABLE:
      locs.append([[i, d], [i - 1, d], [i - 2, d]])
    if d > 2 and board[i][d] == PEG and board[i][d - 1] == PEG and board[i][d- 2] == AVAILABLE:
      locs.append([[i, d], [i, d - 1], [i, d - 2]])

  return locs

def unapplyMove(board, move):
  pegA = move[0]
  pegB = move[1]
  result = move[2]
  board[result[0]][result[1]] = AVAILABLE
  board[pegA[0]][pegA[1]] = PEG
  board[pegB[0]][pegB[1]] = PEG
  return board

def applyMove(board, move):
  pegA = move[0]
  pegB = move[1]
  result = move[2]
  board[pegA[0]][pegA[1]] = AVAILABLE
  board[pegB[0]][pegB[1]] = AVAILABLE
  board[result[0]][result[1]] = PEG
  return board

def unapplyMoveToPegLocations(pegLocations, move):
  pegA = move[0]
  pegB = move[1]
  result = move[2]
  del pegLocations[result[0] * 5 + result[1]]
  pegLocations[pegA[0] * 5 + pegA[1]] = [pegA[0], pegA[1]]
  pegLocations[pegB[0] * 5 + pegB[1]] = [pegB[0], pegB[1]]
  return pegLocations

def applyMoveToPegLocations(pegLocations, move):
  pegA = move[0]
  pegB = move[1]
  result = move[2]
  del pegLocations[pegA[0] * 5 + pegA[1]]
  del pegLocations[pegB[0] * 5 + pegB[1]]
  pegLocations[result[0] * 5 + result[1]] = [result[0], result[1]]
  return pegLocations

def getMin(board, numPegs, pegLocations):
  validMoves = getValidMoves(board, pegLocations)

  if len(validMoves) == 0 or numPegs == 1:
    return numPegs
  
  minn = 1000000
  for move in validMoves:
    board = applyMove(board, move)
    pegLocations = applyMoveToPegLocations(pegLocations, move)
    result = getMin(board, numPegs - 1, pegLocations)
    if result < minn:
      minn = result
    board = unapplyMove(board, move)
    pegLocations = unapplyMoveToPegLocations(pegLocations, move)

  return minn



def execute():
  board = []
  pegCount = 0
  pegLocations = {}
  for i in range(0, 5):
    row = []
    line = sys.stdin.readline()
    for d in range(0, 5):
      char = line[d]

      if char == '#':
        row.append(UNPLAYABLE)
      elif char == '.':
        row.append(AVAILABLE)
      else:
        row.append(PEG)
        pegLocations[i * 5 + d] = [i, d]
        pegCount += 1
    board.append(row)
  print "The best case ends with " + str(getMin(board, pegCount, pegLocations)) + " pegs."

testcases = int(sys.stdin.readline())

for i in range(0, testcases):
  execute()
