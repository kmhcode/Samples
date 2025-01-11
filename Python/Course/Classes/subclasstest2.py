from ads.boards import *

def order(board, count):
    d = 0 if count < 10 else 0.05
    e = 0
    if isinstance(board, Wasteful):
        e = 0.75 * board.scrap()
    return count * (1 -d) * board.price() + e


n = int(input('Number of Signboards: '))

a = RectangularBoard(Medium.wood, 9.5, 4.5)
print('Total payment for rectangular boards: %.2f' % order(a, n))
b = CircularBoard(Medium.metal, 7.5)
print('Total payment for circular boards: %.2f' % order(b, n))
