
# hi = "hello there"
# name ="Nil"
# str = hi + " " + name # concatenation
# print(str)

# silly = hi + (" " + name) * 3
# print(silly)

# print("my fav num is", name, ".", "name = ", name) # insert , means space

# text = input("type anything")
# print(5 * text)

# print("=========================")
# input_ = input("type any number")
# print(type(input_))
# num = int(input_)
# print(type(num))

# x = float(input("enter a number for x: "))
# y = float(input("enter a number for y: "))
# if x == y:
#     print("x and y are equal")
# elif x < y:
#     print("x is less than y")
# else: 
#     print("x is greater than y")

# n = input("you're in the lost foreset. go left or right? ")
# while n == "right":
#     n = input("you're in the lost foreset. go left or right? ")
# print("\nyou got out of the lost forest!\n\o/")

mysum = 0
for i in range (7,10): # loop until value is stop - 1 -> stop is exclusive -> interate over 7,8,9
    mysum += i
print(mysum)


mysum = 0
for i in range (5,11,2):
    mysum += i
print(mysum)