def binarysearch(arr, target) -> int:
    l = 0
    r = len(arr) - 1

    while l <= r:
        m = (l + r) // 2
        if arr[m] == target:
            return m
        elif arr[m] < target:
            l = m + 1
        else:
            r = m - 1
    return -1

def __main__():
    arr = [1,2,3,4]
    target = 3
    idx = binarysearch(arr, target)
    print("idx = {idx}".format(idx=idx))

if __name__ == '__main__':
    __main__()