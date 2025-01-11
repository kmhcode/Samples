from interval import Interval

def speed(distance, duration):
    result = 3.6 * distance / duration.time()
    duration.reset()
    return result

Interval.logging = True
a = Interval(2, 30)
print(a)
b = Interval(3, 95)
print(b)
c = a + b
print(c)
print('Speed = %.1f' % speed(5000, c))
print(c)
