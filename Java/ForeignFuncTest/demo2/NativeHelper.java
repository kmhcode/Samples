
import java.lang.invoke.MethodHandle;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.Linker;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;


class NativeHelper {

	public static MethodHandle importFunction(SymbolLookup lookup, String func, MemoryLayout result, MemoryLayout... parameters) {
		if(lookup == null)
			lookup = SymbolLookup.loaderLookup();
		return Linker.nativeLinker().downcallHandle(
			lookup.find(func).get(), FunctionDescriptor.of(result, parameters));
	}
	
	public static MemorySegment exportMethod(Arena scope, MethodHandle method, MemoryLayout result, MemoryLayout... parameters) {
		if(scope == null)
			scope = Arena.global();
		return Linker.nativeLinker().upcallStub(
			method, FunctionDescriptor.of(result, parameters), scope);
	}

	private NativeHelper() {}
}

