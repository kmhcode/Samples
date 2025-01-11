import banners

def order(poster, quantity):
    d = 0 if quantity < 10 else 0.05
    return quantity * poster.price() * (1 - d)

w = float(input('Banner width: '))
h = float(input('Banner height: '))
n = int(input('Number of Banners: '))

a = banners.Banner(w, h)
b = banners.HardBanner(w, h, 3)
print('Total payment for regular banner: %.2f' % order(a, n))
print('Total payment for rigid banner: %.2f' % order(b, n))
