import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
//import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static java.lang.foreign.ValueLayout.*;

class Program {

    static boolean isFavorite(long num) {
        return (num - 1) % 4 == 0;
    }

    public static void main(String[] args) throws Throwable {
        long m = Long.parseUnsignedLong(args[0]);
        int n = Integer.parseInt(args[1]);
        var primesLibLookup = SymbolLookup.libraryLookup("../native/libprimes.so", Arena.global());
        var primesFetchHandle = Linker.nativeLinker().downcallHandle(
            primesLibLookup.find("primes_fetch").get(), 
            FunctionDescriptor.of(JAVA_LONG, JAVA_LONG, JAVA_INT, ADDRESS, ADDRESS));
        var isFavoriteHandle = MethodHandles.lookup().findStatic(
            Program.class, 
            "isFavorite", 
            MethodType.methodType(boolean.class, long.class)); 
        try(var arena = Arena.ofConfined()){
            var store = arena.allocate(JAVA_LONG, n);
            //primesFetchHandle.invoke(m, n, MemorySegment.NULL, store);
            var isFavoriteStub = Linker.nativeLinker().upcallStub(
                isFavoriteHandle, 
                FunctionDescriptor.of(JAVA_BOOLEAN, JAVA_LONG), 
                arena);
            primesFetchHandle.invoke(m, n, isFavoriteStub, store);
            for(int i = 0; i < n; i++)
                System.out.println(store.getAtIndex(JAVA_LONG, i));
        }
    }
}

