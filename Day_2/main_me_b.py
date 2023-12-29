import time

def calculate_valid_games_from_file(file_path='data_b.txt'):
    total_sum = 0
    with open(file_path, 'r') as file:
        for line in file:
            print(line)
            min_cubes = get_minimum_cubes(line)
            total_sum += min_cubes[0] * min_cubes[1] * min_cubes[2] 
    return total_sum

def get_minimum_cubes(line: str):
    color_cubes = {'red': 0, 'green': 0, 'blue': 0}
    words = line.split(' ')
    for i in range(3, len(words), 2):
        cubes = int(words[i - 1])
        color = words[i][0:-1]
        color_cubes[color] = max(color_cubes[color], cubes)
    return color_cubes['red'], color_cubes['green'], color_cubes['blue']

t1 = time.time()
total_sum = calculate_valid_games_from_file()
t2 = time.time()
print('time -> ', t2-t1)
print(f"total_sum: {total_sum}")