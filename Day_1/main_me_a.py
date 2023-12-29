import re
import time

def calculate_sum_from_file(file_path):
    total_sum = 0
    with open(file_path, 'r') as file:
        for line in file:
            digits = find_first_and_last_digit(line)
            total_sum += digits
    return total_sum

def find_first_and_last_digit(string):
    match = re.findall(r'\d', string)
    if match:
        return int(f'{match[0]}{match[len(match) - 1]}')
    else:
        return None

# Replace 'data.txt' with the path to your file
file_path = 'data.txt'
t1 = time.time()
sum_result = calculate_sum_from_file(file_path)
t2 = time.time()
print('time -> ', t2-t1)
print(f"Total Sum: {sum_result}")
