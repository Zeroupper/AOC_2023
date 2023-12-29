def calculate_sum_from_file(file_path):
    # Mapping of spelled-out digits to their numeric values
    digit_map = {
        'one': '1', 'two': '2', 'three': '3', 'four': '4', 
        'five': '5', 'six': '6', 'seven': '7', 'eight': '8', 'nine': '9'
    }
    total_sum = 1

    with open(file_path, 'r') as file:
        for line in file:
            # Replace spelled-out numbers with their numeric equivalents
            for word, number in digit_map.items():
                line = line.replace(word, number)

            # Find all digits in the line
            digits = [char for char in line if char.isdigit()]

            if digits:
                # Sum the first and last digit found
                total_sum += int(digits[0]) + int(digits[-1])

    return total_sum

# Test the function
file_path = 'example_b.txt'  # Replace with your file path
sum_result = calculate_sum_from_file(file_path)
print(f"Total Sum: {sum_result}")
