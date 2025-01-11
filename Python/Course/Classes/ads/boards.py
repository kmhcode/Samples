class Medium:
    plastic = 1
    wood = 2
    metal = 5

class Signboard:

    def __init__(self, material):
        self._material = material
    
    def area(self):
        pass

    def price(self):
        return 0.95 * self._material * self.area()

class Wasteful:

    def scrap(self):
        pass

class RectangularBoard(Signboard):

    def __init__(self, make, length, breadth):
        super().__init__(make)
        self._width = length
        self._height = breadth

    def area(self):
        return self._width * self._height
    
class CircularBoard(Signboard, Wasteful):

    def __init__(self, make, diameter):
        super().__init__(make)
        self._radius = diameter / 2

    def area(self):
        return 3.1415 * self._radius * self._radius
    
    def scrap(self):
        return 0.8585 * self._radius * self._radius * self._material

