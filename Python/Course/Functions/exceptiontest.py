def compute(first, last, level = 1):
    result = 0
    if first > last:
        raise Exception('Invalid range')
    return sum(num ** level for num in range(first, last + 1))

lower = int(input('Lower Limit: '))
upper = int(input('Upper Limit: '))
try:
    print('Result of simple computation  =', compute(lower, upper))
    print('Result of comples computation =', compute(lower, upper, 3))
except Exception as err:
    print(err)
finally:
    print('Goodbye!')


