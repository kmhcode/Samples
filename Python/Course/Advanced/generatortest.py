from interval import Interval as Duration

class SimpleStack:

    class _Node:

        def __init__(self, entry, below):
            self._entry = entry
            self._below = below
        
    def __init__(self):
        self._top = None

    def push(self, item):
        self._top = SimpleStack._Node(item, self._top)

    def pop(self):
        node = self._top
        self._top = node._below
        return node._entry
    
    def empty(self):
        return self._top is None

    def __iter__(self):
        current = self._top
        while current is not None:
            yield current._entry
            current = current._below
    
a = SimpleStack()
a.push('Monday')
a.push('Tuesday')
a.push('Wednesday')
a.push('Thursday')
a.push('Friday')
while not a.empty():
    print(a.pop())
print('------------------------')
b = SimpleStack()
b.push(Duration(4, 31))
b.push(Duration(7, 42))
b.push(Duration(5, 23))
b.push(Duration(6, 14))
for i in b:
    print(i)

