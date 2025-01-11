num = int(input('Positive Integer: '))
sum = 0
while num > 0 :
    sum = sum + num % 10
    num = num // 10
print('Sum of Digits =', sum)

