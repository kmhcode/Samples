import sys


def singleton(target):

    instances = {}

    def activator(*args, **kwargs):
        if target not in instances:
            instances[target] = target(*args, **kwargs)
        return instances[target]
    
    return activator


@singleton
class Greeter:

    def __init__(self):
        self._count = 0
        print('Greeter instance activated')

    def meet(self, person):
        self._count += 1
        return f'Hello {person}'

    @property
    def greetings(self):
        return self._count
    
for name in sys.argv[1:]:
    g = Greeter()
    print(g.meet(name), '- greeting number', g.greetings)
