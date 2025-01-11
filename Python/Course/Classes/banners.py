class Banner:

    def __init__(self, width = 20, height = 5):
        self._width = width
        self._height = height

    def resize(self, width, height):
        self._width = width
        self._height = height
    
    def price(self):
        region = self._width * self._height
        rate = 0.80 if region >= 100 else 0.95
        return region * rate

class HardBanner(Banner):

    def __init__(self, width, height, thickness):
       	Banner.__init__(self, width)
        self._thickness = thickness

    def price(self):
        return 0.75 * self._thickness * Banner.price(self)


