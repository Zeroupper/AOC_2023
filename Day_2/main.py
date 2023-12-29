import re
import time

def is_game_possible(game_data, max_red, max_green, max_blue):
    max_seen_red, max_seen_green, max_seen_blue = 0, 0, 0
    for reveal in game_data:
        # Find numbers of each color in the reveal
        reds = re.findall(r'(\d+) red', reveal)
        greens = re.findall(r'(\d+) green', reveal)
        blues = re.findall(r'(\d+) blue', reveal)
        
        # Update max seen cubes for each color
        if reds:
            max_seen_red = max(max_seen_red, int(reds[0]))
        if greens:
            max_seen_green = max(max_seen_green, int(greens[0]))
        if blues:
            max_seen_blue = max(max_seen_blue, int(blues[0]))

    return max_seen_red <= max_red and max_seen_green <= max_green and max_seen_blue <= max_blue

def sum_possible_game_ids(file_path):
    with open(file_path, 'r') as file:
        total_sum = 0
        for line in file:
            game_id = int(re.search(r'Game (\d+):', line).group(1))
            game_data = line.split(':')[1].split(';')
            if is_game_possible(game_data, 12, 13, 14):
                total_sum += game_id
        return total_sum

# Replace 'games.txt' with the path to your file
file_path = 'data.txt'
t1 = time.time()
result = sum_possible_game_ids(file_path)
t2 = time.time()
print('time -> ', t2-t1)
print(f"Total Sum of Possible Game IDs: {result}")
