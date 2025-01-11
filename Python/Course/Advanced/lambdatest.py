class Series:

    def __init__(self, terms):
        self._terms = terms

    def sum(self, sequence):
        total = 0
        for term in range(self._terms):
            total += sequence(term)
        return total
    
def expense(year):
    return 5 * year - 2

n = int(input('Number of Years: '))
s = Series(n)
print('Total Expense =', s.sum(expense))
print('Total Income  =', s.sum(lambda y: y * y + 1))

