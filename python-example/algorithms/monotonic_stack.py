def next_greater_element(arr):
    n = len(arr)
    result = [-1] * n
    stack = []

    for i in range(n):
        while stack and arr[stack[-1]] < arr[i]:
            idx = stack.pop()
            result[idx] = arr[i]
        stack.append(i)
    return result


def next_smaller_element(arr):
    n = len(arr)
    result = [-1] * n
    stack = []

    for i in range(n):
        # 현재 원소가 더 작으면 stack의 원소들 처리
        while stack and arr[stack[-1]] > arr[i]:
            idx = stack.pop()
            result[idx] = arr[i]
        stack.append(i)

    return result

def __main__():
    arr = [4, 5, 2, 10, 8]
    print(next_greater_element(arr))
    print(next_smaller_element(arr))

if __name__ == '__main__':
    __main__()