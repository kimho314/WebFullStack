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

an_letters = "aefhilmnorsxAEFHILMNORSX"
word = input("I will cheer for you! Enter a word: ")
tiems = int(input("Enthusiasm level (1-10): "))

i = 0
while i < len(word):
    char = word[i]
    if char in an_letters:
        print("Give me an " + char + "! " + char)
    else:
        print("Give me a " + char + "! " + char)
    i += 1
print("What does that spell?")
for i in range(tiems):
    print(word, "!!!")