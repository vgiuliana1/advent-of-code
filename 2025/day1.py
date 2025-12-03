DIAL_START = 50
CURRENT_POSITION = DIAL_START
zeros = 0

with open('input/day1.txt', 'r') as file:
    lines = file.readlines()
    for line in lines:
        line = line.strip()
        direction = line[0]
        amount = int(line[1:])
        
        CURRENT_POSITION += amount if direction == 'R' else -amount
        
        CURRENT_POSITION %=  100
        
        if CURRENT_POSITION == 0:
            zeros += 1

print("Zeros crossed:", zeros)