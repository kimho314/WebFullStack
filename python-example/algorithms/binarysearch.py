def binary_search(arr, target):
    l = 0
    r = len(arr) - 1
    while l <= r:
        m = l + (r - l) // 2
        if arr[m] < target:
            l = m + 1
        elif arr[m] > target:
            r = m - 1
        else:
            return m
    return -1

def search_lower_bound(arr, target):
        left, right = 0, len(arr)
        while left < right:
            mid = (left + right) // 2
            if arr[mid] < target:
                left = mid + 1
            else:
                right = mid
        return left

def search_upper_bound(arr, target):
        left, right = 0, len(arr)
        while left < right:
            mid = (left + right) // 2
            if arr[mid] <= target:
                left = mid + 1
            else:
                right = mid
        return left

def __main__():
    arr = [1,2,3,4]
    target = 3
    idx = binary_search(arr, target)
    print("idx = {idx}, value = {value}".format(idx=idx, value=arr[idx]))

    arr = [1, 2, 2, 2, 3, 5]
    target = 2
    leftmost_idx = search_lower_bound(arr, target)
    print("leftmost_idx = {idx}, value = {value}".format(idx=leftmost_idx, value=arr[leftmost_idx]))

    arr = [1, 2, 2, 2, 3, 5]
    target = 2
    rightmost_idx = search_upper_bound(arr, target)
    print("rightmost_idx = {idx}, value = {value}".format(idx=rightmost_idx, value=arr[rightmost_idx]))

if __name__ == '__main__':
    __main__()