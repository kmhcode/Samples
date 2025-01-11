from interval import Interval

class IntervalClock:

    def __init__(self, min, sec):
        self._info = (min, sec)
    
    def __enter__(self):
        print("Starting timer for Interval...")
        return Interval(self._info[0], self._info[1])
    
    def __exit__(self, *err):
        print("Stopped timer for the Interval.")

class SmallInterval(Exception):
    
    def __init__(self):
        super().__init__('Insufficient time');

def run(sec):
    with IntervalClock(0, sec) as i:
        if sec < 10:
            raise SmallInterval
        print('Running timer for Interval', i)

Interval.logging = True
t = int(input('Time in seconds: '))
try:
    run(t)
except SmallInterval as e:
    print('Error:', e)


