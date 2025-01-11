age = int(input('Age: '))
if age == 16:
    rate = 75.0
elif age == 18:
    rate = 125.0
elif age == 21:
    rate = 150.0
elif age == 50:
    rate = 200.0
else:
    rate = 50
print('Gift Amount = ', age * rate)
