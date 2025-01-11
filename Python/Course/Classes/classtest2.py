class Geometry:
    Rectangular = 1
    Triangular = 2
    Elliptical = 3

class Banner:

    def __init__(self):
        self._region = 100
        self._shape = Geometry.Rectangular

    def resize(self, width, height):
        self._region = width * height
    def reshape(self, shape):
        self._shape = shape
    
    def price(self):
        region = self._region
        rate = 0.80 if region >= 100 else 0.95
        if self._shape == Geometry.Triangular:
            wastage = 0.5
        elif self._shape == Geometry.Elliptical:
            wastage = 0.22
        else:
            wastage = 0
        return region * rate * (1 - 0.6 * wastage)


b = Banner()
print('Price of standard banner: %.2f' % b.price())
w = float(input("Banner Width : "))
h = float(input("Banner Height: "))
c = Banner()
c.resize(w, h)
print('Price of custom rectangular banner: %.2f' % c.price())
c.reshape(Geometry.Elliptical)
print('Price of custom elliptical banner: %.2f' % c.price())
