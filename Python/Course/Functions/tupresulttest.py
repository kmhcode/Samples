def average(first, second):
    avg = (first + second) / 2
    dev = (first - second) / 2 if first > second else (second - first) / 2
    return avg, dev

p = float(input('First value: '))
q = float(input('Second value: '))
a, d = average(p, q)
print('Average is %f with a deviation of %.3f' % (a, d))
