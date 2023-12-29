def find_destination_number(source, map_data):
    for dest_start, src_start, length in map_data:
        if src_start <= source < src_start + length:
            return dest_start + (source - src_start)
    return source  # Return the source number if no mapping is found

def process_seed_number(seed, mappings):
    for map_data in mappings:
        seed = find_destination_number(seed, map_data)
    return seed

def read_data_from_file(file_path):
    with open(file_path, 'r') as file:
        lines = file.readlines()

    seed_ranges = list(map(int, lines[0].strip().split(':')[1].split()))
    
    mappings = []
    i = 3  # Adjust based on where mapping data starts in the file
    while i < len(lines):
        map_data = []
        while i < len(lines) and lines[i].strip():
            dest_start, src_start, length = map(int, lines[i].strip().split())
            map_data.append((dest_start, src_start, length))
            i += 1
        i += 2  # Skip empty line and header of the next map
        mappings.append(map_data)

    return seed_ranges, mappings

def find_lowest_location(seed_ranges, mappings):
    lowest_location = float('inf')
    for i in range(0, len(seed_ranges), 2):
        start, length = seed_ranges[i], seed_ranges[i+1]
        for seed in range(start, start + length):
            final_location = process_seed_number(seed, mappings)
            lowest_location = min(lowest_location, final_location)
    return lowest_location

file_path = 'data.txt'
seed_ranges, mappings = read_data_from_file(file_path)

lowest_location = find_lowest_location(seed_ranges, mappings)
print(f"Lowest location number: {lowest_location}")
