import static java.lang.foreign.ValueLayout.*;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

class Program {

    static boolean isFavorite(long num) {
        return (num - 1) % 4 == 0;
    }

    public static void main(String[] args) throws Throwable {
        long m = Long.parseUnsignedLong(args[0]);
        int n = Integer.parseInt(args[1]);
        SymbolLookup primesLibLookup = SymbolLookup.libraryLookup("native/a64/libprimes.so", Arena.global());
        MethodHandle primesFetchHandle = Linker.nativeLinker().downcallHandle(
            primesLibLookup.findOrThrow("primes_fetch"), 
            FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_INT, ADDRESS, ADDRESS));
        MethodHandle isFavoriteHandle = MethodHandles.lookup().findStatic(
            Program.class, "isFavorite", 
            MethodType.methodType(boolean.class, long.class));
        try(var arena = Arena.ofConfined()){
            MemorySegment store = arena.allocate(JAVA_LONG, n);
            //primesFetchHandle.invokeExact(m, n, MemorySegment.NULL, store);
            MemorySegment isFavoriteStub = Linker.nativeLinker().upcallStub(
                isFavoriteHandle,
                FunctionDescriptor.of(JAVA_BOOLEAN, JAVA_LONG),
                arena);
            primesFetchHandle.invokeExact(m, n, isFavoriteStub, store);
            for(int i = 0; i < n; ++i)
                System.out.println(store.getAtIndex(JAVA_LONG, i));
        }
    }
}
