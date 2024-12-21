import java.io.File;
import java.lang.foreign.ValueLayout;

class Program {

    public static void main(String[] args) throws Throwable {
        long m = Long.parseLong(args[0]);
        long n = Long.parseLong(args[1]);
        System.load(new File("native/libdijkstra.so").getAbsolutePath());
        var gcdHandle = NativeLinking.importFunction(null, "GCD", ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG);
        long g = (long)gcdHandle.invoke(m, n);
        System.out.printf("G.C.D = %d%n", g);
    }
}
