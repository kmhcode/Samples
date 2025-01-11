lower = int(input('Lower Limit: '))
upper = int(input('Upper Limit: '))
result = 0
for current in range(lower, upper + 1):
    result += current * current
print('Result of Coumputation =', result)

# range(5) => [1, 2, 3, 4]
# range(3, 7) => [3, 4, 5, 6]
# range(1, 9, 2) => [1, 3, 5, 7]
# range(9, 1, -2) => [9, 7, 5, 3]
