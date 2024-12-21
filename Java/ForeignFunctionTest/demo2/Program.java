import static java.lang.foreign.ValueLayout.*;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;

class Program {

    static boolean isFavorite(long num) {
        return (num - 1) % 4 == 0;
    }

    public static void main(String[] args) throws Throwable {
        long m = Long.parseUnsignedLong(args[0]);
        int n = Integer.parseInt(args[1]);
        var primesLookup = SymbolLookup.libraryLookup("native/libprimes.so", Arena.global());
        var primesFetchHandle = NativeLinking.importFunction(primesLookup, "primes_fetch", JAVA_LONG, JAVA_LONG, JAVA_INT, ADDRESS, ADDRESS);
        try(var arena = Arena.ofConfined()){
            var selectedSegment = arena.allocate(JAVA_LONG, n);
            if(n <= 5)
                primesFetchHandle.invoke(m, n, MemorySegment.NULL, selectedSegment);
            else{
                var selectorStubSegment = NativeLinking.exportMethod(arena, Program.class, "isFavorite", JAVA_BOOLEAN, JAVA_LONG);
                primesFetchHandle.invoke(m, n, selectorStubSegment, selectedSegment);
            }
            long[] primes = selectedSegment.toArray(JAVA_LONG);
            for(long prime : primes)
                System.out.println(prime);
        }
    }
}
