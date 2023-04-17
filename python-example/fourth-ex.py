def is_even(i):
    """
    Input: i, a positive int
    Returns True if i is even, othersie False
    """
    print("inside is_even")
    return i%2 == 0

print(is_even(3))

def is_even_without_retrun(i):
    """
    Input: i, a positive int
    Returns True if i is even, othersie False
    """
    print("inside is_even")
    remainder =  i%2

print(is_even_without_retrun(3))

def g(x):
    def h():
        x = 'abc'
    x = x + 1
    print('g: x = ', x)
    h()
    return x
x = 3
z = g(x)
print(z)