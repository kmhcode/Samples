#pragma once

typedef unsigned long long pnum_t;

#ifdef __cplusplus
extern "C" {
#endif

pnum_t primes_fetch(pnum_t start, int count, int (*selector)(pnum_t), pnum_t* selected);

#ifdef __cplusplus
}
#endif

