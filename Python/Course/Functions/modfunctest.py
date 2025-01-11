import investment

p = float(input('Yearly Payment : '))
n = int(input('Number of Years: '))
print('Future value in riskless investment: %.2f' % investment.future_value(p, n, False))
print('Future value in riskful investment : %.2f' % investment.future_value(p, n, True))
