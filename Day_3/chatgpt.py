import time


def sum_part_numbers_from_file(file_path):
    def is_symbol(ch):
        return ch not in ['.', ' ', '\n', '\t', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9']

    def is_adjacent_to_symbol(x, y, schematic):
        for dx in range(-1, 2):
            for dy in range(-1, 2):
                nx, ny = x + dx, y + dy
                if 0 <= nx < len(schematic) and 0 <= ny < len(schematic[nx]):
                    if is_symbol(schematic[nx][ny]):
                        return True
        return False

    total_sum = 0
    visited = set()
    with open(file_path, 'r') as file:
        schematic = [line.strip() for line in file]

        for i, row in enumerate(schematic):
            j = 0
            while j < len(row):
                if row[j].isdigit() and (i, j) not in visited:
                    number_str = ''
                    start_j = j
                    while j < len(row) and row[j].isdigit():
                        number_str += row[j]
                        visited.add((i, j))
                        j += 1

                    if any(is_adjacent_to_symbol(i, k, schematic) for k in range(start_j, j)):
                        total_sum += int(number_str)
                else:
                    j += 1

    return total_sum

file_path = 'data.txt'
t1 = time.time()
result = sum_part_numbers_from_file(file_path)
t2 = time.time()
print(t2 - t1)
print(f"Total Sum of Part Numbers: {result}")
