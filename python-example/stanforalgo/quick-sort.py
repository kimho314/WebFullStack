import sys
sys.setrecursionlimit(10**6)

ans = 0

"""
pivot is first element of array
"""
# def partition(arr, l, r):
#     global ans
#     ans += (r-l-1)
#     print("len",r-l-1,"ans",ans, sep=" : ")
#     p = arr[l]
#     i = l + 1
#     for j in range(i, r):
#         if arr[j] < p:
#             arr[i], arr[j] = arr[j], arr[i]
#             i = i + 1
#     arr[l], arr[i-1] = arr[i-1], arr[l]
#     return i - 1

# def quicksort(arr, l, r):
#     if l < r:
#         pivot_index = partition(arr, l, r)
#         quicksort(arr, l, pivot_index)
#         quicksort(arr, pivot_index + 1, r)

"""
pivot is last element of array
""" 
def partition(arr, l, r):
    global ans
    ans += (r-l-1)
    print("len",r-l-1,"ans",ans,"arr",arr[l:r], sep=" : ")
    p = arr[l]
    i = l + 1
    for j in range(i, r):
        if arr[j] < p:
            arr[i], arr[j] = arr[j], arr[i]
            i = i + 1
    arr[l], arr[i-1] = arr[i-1], arr[l]
    return i - 1

def quicksort(arr, l, r):
    if l < r:
        arr[l],arr[r-1] = arr[r-1],arr[l]
        pivot_index = partition(arr, l, r)
        quicksort(arr, l, pivot_index)
        quicksort(arr, pivot_index + 1, r)


"""
pivot is "median-of-three" pivot rule
""" 
# def median_of_three(arr, l, r):
#     mid = (l + r - 1) // 2
#     if arr[l] <= arr[mid] <= arr[r - 1] or arr[r - 1] <= arr[mid] <= arr[l]:
#         return mid
#     elif arr[mid] <= arr[l] <= arr[r - 1] or arr[r - 1] <= arr[l] <= arr[mid]:
#         return l
#     else:
#         return r - 1

# def partition(arr, l, r):
#     global ans
#     ans += (r-l-1)
#     print("len",r-l-1,"ans",ans, sep=" : ")
#     pivot_index = median_of_three(arr, l, r)
#     arr[l], arr[pivot_index] = arr[pivot_index], arr[l]
#     p = arr[l]
#     i = l + 1
#     for j in range(i, r):
#         if arr[j] < p:
#             arr[i], arr[j] = arr[j], arr[i]
#             i += 1
#     arr[l], arr[i - 1] = arr[i - 1], arr[l]
#     return i - 1

# def quicksort(arr, l, r):
   
#     if l < r - 1:
#         pivot_index = partition(arr, l, r)
#         quicksort(arr, l, pivot_index)
#         quicksort(arr, pivot_index + 1, r)

# Example usage:
# arr = [1,6,8,10,7,5,2,9,4,3]
arr = []
with open('QuickSort.txt', 'r') as file:
    # arr = file.readlines()
    for line in file:
        arr.append(int(line.strip()))

quicksort(arr, 0, len(arr))
print(arr)
print(ans)
