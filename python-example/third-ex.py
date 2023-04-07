s = "abc"
print(len(s))
print("======")
print(s[0])
print(s[1])
print(s[-1])
print(s[-2])

s = "abcdefghi"
print(s[3:6])
print(s[3:6:2])
print(s[::])
print(s[::-1]) # hgfedcba

s = "hello"
#s[0] = 'y' # gives an error -> strings are immutable

s = "abcdefghi"
for char in s:
    if char == 'i' or char == 'u':
        print("there is an i or u")