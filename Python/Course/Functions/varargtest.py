def average(first, second, *remaining):
    simple_sum = first + second
    square_sum = first * first + second * second
    count = 2 + len(remaining)
    for val in remaining:
        simple_sum += val
        square_sum += val * val
    avg = simple_sum / count
    dev = (square_sum / count - avg * avg) ** 0.5
    return avg, dev

a, d = average(3.2, 4.3, 5.4, 6.5, 7.6)
print(f'Average is {a} with a deviation of {d:.3f}')
