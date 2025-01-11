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

    class _Iterator:

        def __init__(self, node):
            self._current = node
        
        def __next__(self):
            if self._current is None:
                raise StopIteration
            item = self._current._entry
            self._current = self._current._below
            return item

    def __iter__(self):
        return SimpleStack._Iterator(self._top)
    
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

