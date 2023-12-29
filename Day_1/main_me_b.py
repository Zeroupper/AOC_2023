import re
import time

numbers = {
    'one':   1,
    'two':   2,
    'three': 3,
    'four':  4,
    'five':  5,
    'six':   6,
    'seven': 7,
    'eight': 8,
    'nine':  9
}

def calculate_sum_from_file(file_path='data.txt'):
    total_sum = 0
    with open(file_path, 'r') as file:
        for line in file:

            digits = find_digits(line)
            min_value = min(digits, key=lambda x: x[0])
            max_value = max(digits, key=lambda x: x[0])
            two_digits = int(f'{min_value[1]}{max_value[1]}')
            total_sum += two_digits
    return total_sum
    
def find_digits(line):
    matched_literals = []
    readed_index = 0
    finded_pos = []

    for num_str in numbers.keys():
        finded_pos.extend(find_all_word_positions(num_str, line))
    for pos in finded_pos:
        value = numbers.get(pos[1] ,-1)
        matched_literals.append((pos[0], value))

    for char in line:
        try:
            matched_literals.append((readed_index, int(char)))
        except ValueError:
            pass            
        finally:
            readed_index += 1

    return matched_literals


def find_all_word_positions(word, string):
    start = 0
    positions = []

    while True:
        # Find the next position of the word
        position = string.find(word, start)

        # If the word is not found, break the loop
        if position == -1:
            break

        # Append the position to the list
        positions.append((position, word))

        # Update the start position for the next search
        start = position + 1

    return positions

t1 = time.time()
sum_result = calculate_sum_from_file()
t2 = time.time()
print('time -> ', t2-t1)
print(f"Total Sum: {sum_result}")
