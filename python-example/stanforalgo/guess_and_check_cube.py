cube = 8
for guess in range(cube + 1):
    if guess**3 >= abs(cube):
        # print("cube root of", cube, "is", guess)
        break
if guess**3 != abs(cube):
    print(cube, 'is not a perfect cube')
else:
    if cube < 0:
        guess = -guess
    print('cube root of ' + str(cube) + ' is ' + str(guess))