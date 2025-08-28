def lowerbound_binarysearch(arr, target) -> int:
    l = 0
    r = len(arr) - 1
    while l < r:
        m = (l + r) // 2
        if arr[m] < target:
            l = m + 1
        else:
            r = m
    return l

def upperbound_binarysearch(arr, target) -> int:
    l = 0
    r = len(arr) - 1
    while l < r:
        m = (l + r) // 2
        if arr[m] > target:
            r = m
        else:
            l = m + 1
    return r - 1

def __main__():
    arr = [1,2,3,3,3,4]
    target = 3
    lower_idx = lowerbound_binarysearch(arr, target) # 2
    upper_idx = upperbound_binarysearch(arr, target) # 4
    print("lower = {lower}, upper = {upper}".format(lower=lower_idx, upper=upper_idx))


if __name__ == '__main__':
    __main__()