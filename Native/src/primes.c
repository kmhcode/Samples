#include "primes.h"

pnum_t primes_factor(pnum_t n)
{
	register pnum_t i;

	if(n < 2)
		return 0;
	if(n == 2 || n % 2 == 0)
		return 2;
	if(n == 3 || n % 3 == 0)
		return 3;
	for(i = 5; i * i <= n; i += 6)
	{
		if(n % i == 0)
			return i;
		if(n % (i + 2) == 0)
			return i + 2;
	}
	return n;
}

void primes_fetch(pnum_t start, int count, int (*selector)(pnum_t), pnum_t* selected)
{
	register pnum_t i, j;
	
	for(i = j = 0; i < count; ++j)
	{
		pnum_t n = start + j;
		if(primes_factor(n) == n && (!selector || selector(n)))
		{
			if(selected)
				selected[i] = n;
			i += 1;
		}
	}
}

