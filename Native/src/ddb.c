#include "asset.h"
#include <math.h>

double depreciation(int life, int after)
{
	return after < life ? 1 - pow(1 - 2.0 / life, after) : 1;
}



