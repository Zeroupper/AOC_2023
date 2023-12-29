def calculate_scratchcard_points(cards):
    total_points = 0

    for card in cards:
        # Split the card data into winning and your numbers
        card_data = card.split(': ')
        winning_numbers = set(map(int, card_data[1].split('|')[0].strip().split()))
        your_numbers = set(map(int, card_data[1].split('|')[1].strip().split()))

        matches = winning_numbers.intersection(your_numbers)
        if matches:
            points = 1 << (len(matches) - 1)  # 1 point for the first match, double for each subsequent match
        else:
            points = 0

        total_points += points

    return total_points

def read_cards_from_file(file_path):
    with open(file_path, 'r') as file:
        cards = file.readlines()
    return cards

# Replace with the actual path to your scratchcard file
file_path = 'data.txt'
cards = read_cards_from_file(file_path)
total_points = calculate_scratchcard_points(cards)
print(f"Total points: {total_points}")