input = 20


def find_prime_list_under_number(number):
    # 이 부분을 채워보세요!
    def is_prime(nums):
        if nums <= 1:
            return False
        for i in range(2, int(nums**0.5) + 1):
            if nums % i == 0:
                return False
        return True
    res = []
    for i in range(number+1):
        if is_prime(i):
            res.append(i)
    return res


result = find_prime_list_under_number(input)
print(result)