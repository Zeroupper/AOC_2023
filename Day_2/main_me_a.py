import re
import time

def calculate_valid_games_from_file(file_path='data.txt'):
    valid_games = 0
    max_red = 12
    max_green = 13
    max_blue = 14

    with open(file_path, 'r') as file:
        for line in file:
            is_Valid = True
            raw_game_ids = re.search(f'\d+:', line).group()

            id = int(raw_game_ids[0:len(raw_game_ids)-1])
            is_Valid = check_validity('blue', line, max_blue) and check_validity('green', line, max_green) and check_validity('red', line, max_red)
            if(is_Valid):
                valid_games += id
    return valid_games

def check_validity(color: str, line: str, max: int):
    color_filter = re.findall(f'\d+ {color}', line)
    for str in color_filter:
        num = re.findall('\d+', str)
        for n in num:
            if(int(n) > max): return False
    return True

t1 = time.time()
valid_games = calculate_valid_games_from_file()
t2 = time.time()
print('time -> ', t2-t1)
print(f"Total valid games: {valid_games}")
