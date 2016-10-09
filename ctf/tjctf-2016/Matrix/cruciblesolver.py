from decimal import *
from matrix import *

getcontext().prec = 500

r = Decimal(0.5)

def generatematrix(n):
    matrix = [[0 for x in range(n)] for y in range(n)]
    for i in range(n):
        if i == 0:
            matrix[i] = [2 + 2 * r, -r] + [Decimal(0)] * (n - 2)
        elif i == n - 1:
            matrix[i] = [Decimal(0)] * (n - 2) + [-r, 2 + 2 * r]
        else:
            matrix[i] = [Decimal(0)] * (i - 1) + [-r, 2 + 2 * r, -r] + [Decimal(0)] * (n - i - 2)
    return matrix

def getreversermatrix(n):
    origmatrix = []
    origmatrix.append([2 * r] + ([Decimal(0)] * (n-1)))
    for i in range(1, n - 1):
        dibber = [Decimal(0)] * n
        dibber[i] = 2 * r
        dibber[i - 1] = r
        dibber[i + 1] = r
        origmatrix.append(dibber)
    origmatrix.append([Decimal(0)] * (n-1) + [2 * r])
    reversermatrix = invert(origmatrix)
    return reversermatrix

file = open("encrypted_b781aa7039a27df2ec6c7babd263f32841141dbcff2645066c22128f28ceea11.txt", "r")

nums = eval(file.readline())
for i in range(0, len(nums)):
    nums[i] = Decimal(nums[i])
print str(nums)
matrix = generatematrix(len(nums))
reversermatrix = getreversermatrix(len(nums))

for step in range(100):
    temporig = mult(matrix, nums)
    nums = mult(reversermatrix, temporig)

flag = ""
for i in nums:
    flag += chr(int(round(i)))
print flag