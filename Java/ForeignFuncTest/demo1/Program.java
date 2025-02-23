import java.lang.foreign.ValueLayout;

class Program {

    public static void main(String[] args) throws Throwable {
        double p = Double.parseDouble(args[0]);
        int l = Integer.parseInt(args[1]);
        System.loadLibrary(args[2]);
        var depreciationHandle = NativeHelper.importFunction(null, "depreciation", ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT);
        for(int n = 1; n < l; ++n){
            double d = (double)depreciationHandle.invokeExact(l, n);
            System.out.printf("%d\t%.2f%n", n, p * (1 - d));
        }
    }
}
