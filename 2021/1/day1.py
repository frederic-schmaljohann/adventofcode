filename = 'input.txt'
cnt = 0

with open(filename) as file:
  lines = file.readlines()
  depths = [int(line.strip()) for line in lines]

for i in range(len(depths)):
  if i != 0 and depths[i] > depths[i-1]:
    cnt = cnt + 1
print(cnt)

cnt = 0
for i in range(len(depths)):
  if i >= 3 and depths[i] + depths[i-1] + depths[i-2] > depths[i-1] + depths[i-2] + depths[i-3]:
    cnt = cnt + 1
print(cnt)
