import time

def is_symbol(c):
    return not (c == '.' or c.isdigit())

def is_part_number(i, j, grid, num_rows, num_columns):
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1), (-1, -1), (-1, 1), (1, -1), (1, 1)]
    for di, dj in directions:
        new_i, new_j = i + di, j + dj
        if 0 <= new_i < num_rows and 0 <= new_j < num_columns:
            if is_symbol(grid[new_i][new_j]):
                return True
    return False

def main():
    start_time = time.time()

    file_name = "data.txt"
    with open(file_name, 'r') as file:
        lines = file.readlines()

    num_rows = len(lines)
    num_columns = len(lines[0].strip())  # Ensure to strip newline characters
    grid = [list(line.strip()) for line in lines]  # Strip each line

    input_time = time.time()

    print((input_time - start_time) * 1000)

    current_num = ""
    total_sum = 0
    is_valid = False

    for i in range(num_rows):
        for j in range(num_columns):

            if grid[i][j].isdigit():
                current_num += grid[i][j]
                if not is_valid and is_part_number(i, j, grid, num_rows, num_columns):
                    is_valid = True
            else:
                if is_valid and current_num:
                    total_sum += int(current_num)
                current_num = ""
                is_valid = False

    print(f"sum -> {total_sum}")

    end_time = time.time()
    print(f"elapsed time -> {(end_time - start_time) * 1000 } ms")

if __name__ == "__main__":
    main()
