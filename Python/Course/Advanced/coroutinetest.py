from time import time
import asyncio

class Computation:

    def __init__(self, first, last, step):
        self._source = range(first, last + 1, step)

    def __await__(self):
        for n in self._source:
            target = time() + n / 10
            while time() < target: pass
            yield n * n
		
async def run_routine(start):
    await Computation(start, 10, 2)

first = run_routine(1)
second = run_routine(2)

try:
    while True:
        print(first.send(None))
        print(second.send(None))
except StopIteration: pass

print('--------------------------------')

async def handle_job(n):
    print('Accepted job', n)
    over = time() + n
    while(time() < over):
        await asyncio.sleep(0)
    print('finished job', n)
    return n * n

async def start():
    r = await asyncio.gather(handle_job(3), handle_job(4))
    print('Result =', r[0] + r[1])

asyncio.run(start())

