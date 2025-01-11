year = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30]
year.append(31)
month = int(input('Month [1-12]: '))
days = year[month - 1]
amount = days * (days + 1) / 2
print('Total Amount = ', amount)
