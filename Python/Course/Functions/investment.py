def future_value(payment, count, risk):
	i = 0.08 if risk else 0.06
	return (payment / i) * ((1 + i) ** count - 1)


