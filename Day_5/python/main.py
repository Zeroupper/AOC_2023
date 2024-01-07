def main():
    file_name = "data.txt"
    
    with open(file_name, 'r') as file:
        lines = file.readlines()

    seeds = []
    all_maps_list = []

    for index, line in enumerate(lines):
        words = line.strip().split()

        if not words:
            continue

        if words[-1] == "map:":
            all_maps_list.append({})
            continue

        if index == 0:
            seeds = [int(word) for word in words[1:]]
        else:
            if len(words) == 3:
                destination_start = int(words[0])
                source_start = int(words[1])
                length = int(words[2])

                destination_range = range(destination_start, destination_start + length)
                source_range = range(source_start, source_start + length)

                for j in range(length):
                    all_maps_list[-1][source_range[j]] = destination_range[j]

    for index in range(len(all_maps_list)):
        new_seeds = []
        for seed in seeds:
            new_seeds.append(all_maps_list[index].get(seed, seed))
        
        seeds = new_seeds

    print(min(seeds) if seeds else None)

if __name__ == "__main__":
    main()