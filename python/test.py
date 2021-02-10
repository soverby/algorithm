import math as m


arr = [ 40, 30, 20, 10, 5, 3, 1]

# print(max(arr))
#
# arr.remove(max(arr))
#
# print(arr)
start = m.floor(len(arr) / 2) - 1

print('start', start)

for i in range(start, -1, -1):
    print(arr[i])