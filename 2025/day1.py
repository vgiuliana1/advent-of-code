def part1(lines: list[str]) -> int:
    DIAL_START = 50
    CURRENT_POSITION = DIAL_START
    zeros = 0

    for line in lines:
        line = line.strip()
        direction = line[0]
        amount = int(line[1:])
        
        CURRENT_POSITION += amount if direction == 'R' else -amount
        
        CURRENT_POSITION %=  100
        
        if CURRENT_POSITION == 0:
            zeros += 1

    return zeros

def part2(lines: list[str]) -> int:
    DIAL_START = 50
    CURRENT_POSITION = DIAL_START
    zeros = 0
    
    for line in lines:
        line = line.strip()
        direction = line[0]
        amount = int(line[1:])
        passes = 0
        
        CURRENT_POSITION += amount if direction == 'R' else -amount
        
        if CURRENT_POSITION < 0 or CURRENT_POSITION > 99:
            passes = CURRENT_POSITION // 100
            zeros += abs(passes)

        CURRENT_POSITION = (CURRENT_POSITION - 1) % 100
        
        if CURRENT_POSITION == 0 and passes == 0:
            zeros += 1

    return zeros

if __name__ == "__main__":
    with open('input/day1.txt', 'r') as file:
        lines = file.readlines()
        print("part 1: ", part1(lines))
        print("part 2: ", part2(lines))