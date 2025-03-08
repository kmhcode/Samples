#include "asset.h"

double depreciation(int life, int after)
{
	return after * (2.0 * life - after + 1) / (life * (life + 1));
}


