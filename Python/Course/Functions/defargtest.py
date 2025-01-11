def compute(first, last, level = 1):
    result = 0
    for num in range(first, last + 1):
        result += num ** level
    return result

lower = int(input('Lower Limit: '))
upper = int(input('Upper Limit: '))
print('Result of simple computation  =', compute(lower, upper))
print('Result of complex computation =', compute(level=3, first=lower, last=upper))

