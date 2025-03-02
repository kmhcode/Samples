using System.Runtime.InteropServices;

class Program 
{
    delegate double DepreciationInvoker(int life, int after);

    static void Main(string[] args)
    {
        double p = double.Parse(args[0]);
        int l = int.Parse(args[1]);
        nint assetLib = NativeLibrary.Load($"native/lib{args[2]}.so");
        nint depreciationPtr = NativeLibrary.GetExport(assetLib, "depreciation");
        var depreciationStub = Marshal.GetDelegateForFunctionPointer<DepreciationInvoker>(depreciationPtr);
        for(int n = 1; n < l; ++n)
        {
            double d = depreciationStub.Invoke(l, n);
            Console.WriteLine("{0}\t{1:0.00}", n, p * (1 - d));
        }
        NativeLibrary.Free(assetLib);
    }
}