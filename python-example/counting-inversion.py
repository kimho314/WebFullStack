def merge_sort(arr):
    if len(arr) <= 1:
        return arr, 0

    mid = len(arr) // 2
    left, inv_count_left = merge_sort(arr[:mid])
    right, inv_count_right = merge_sort(arr[mid:])
    merged, inv_count_merge = merge(left, right)

    inv_count_total = inv_count_left + inv_count_right + inv_count_merge
    return merged, inv_count_total

def merge(left, right):
    result = []
    inv_count = 0
    i = j = 0

    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            inv_count += len(left) - i
            j += 1

    result.extend(left[i:])
    result.extend(right[j:])
    return result, inv_count

def count_inversions(arr):
    _, inv_count = merge_sort(arr)
    return inv_count

arr = []
# Alternatively, you can read the file line by line
with open('IntegerArray.txt', 'r') as file:
    # arr = file.readlines()
    for line in file:
        arr.append(int(line.strip()))
        # print(line.strip())  # strip() removes leading liand trailing whitespaces

# Example usage:
# arr = [1, 3, 5, 2, 4, 6]
result = count_inversions(arr)
print("Number of inversions:", result)