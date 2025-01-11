class Banner:

    def __init__(self):
        self._width = 20
        self._height = 5

    def resize(self, width, height):
        self._width = width
        self._height = height
    
    def price(self):
        region = self._width * self._height
        rate = 0.80 if region >= 100 else 0.95
        return region * rate


b = Banner()
print('Price of standard banner: %.2f' % b.price())
w = float(input("Banner Width : "))
h = float(input("Banner Height: "))
c = Banner()
c.resize(w, h)
print('Price of custom banner: %.2f' % c.price())