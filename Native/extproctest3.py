from ctypes import *

dijkstra = CDLL("./libdijkstra.so")
m = int(input("Numerator : "))
n = int(input("Denominator: "))
g = dijkstra.GCD(m, n)
print("Reduced Fraction = %d/%d" % (m / g, n / g))

