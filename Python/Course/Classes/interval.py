class Interval:
    
    logging = False
    _nid = 1

    def _log(msg):
        if Interval.logging:
            print(msg)

    def __init__(self, min, sec):
        self._id = Interval._nid
        self._minutes = min + sec // 60
        self._seconds = sec % 60
        Interval._nid += 1
        Interval._log(f'Interval<{self._id}> activated')
    
    def time(self):
        return 60 * self._minutes + self._seconds

    def reset(self, sec = 0):
        self._minutes = sec // 60
        self._seconds = sec % 60

    def __str__(self):
        return f'{self._minutes}:{self._seconds:02d}'
    
    def __add__(self, other):
        return Interval(self._minutes + other._minutes, self._seconds + other._seconds)

    def __del__(self):
        Interval._log(f'Interval<{self._id}> deactivated')

