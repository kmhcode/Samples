def banner_price(width, height):
    rate = 0.80 if width > height else 0.95
    return width * height * rate

w = float(input('Width of Banner: '))
h = float(input('Height of Banner: '))
n = int(input('Number of Banners: '))

payment = n * banner_price(w, h)
print('Total Payment = %.2f' % payment)
