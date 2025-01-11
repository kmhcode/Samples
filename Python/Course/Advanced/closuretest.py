def fibonacci():
    a, b = 0, 1
    def next():
        nonlocal a, b
        a, b = a + b, a
        return a
    return next

f = fibonacci()
for i in range(10):
    print(f())
