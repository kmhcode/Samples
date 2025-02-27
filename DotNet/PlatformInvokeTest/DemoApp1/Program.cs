using System.Runtime.InteropServices;

double p = double.Parse(args[0]);
int l = int.Parse(args[1]);
nint assetLib = NativeLibrary.Load(args[2]);
nint depreciationPtr = NativeLibrary.GetExport(assetLib, "depreciation");
var depreciationStub = Marshal.GetDelegateForFunctionPointer<DepreciationFunc>(depreciationPtr);
for(int n = 1; n < l; ++n)
{
    double d = depreciationStub.Invoke(l, n);
    Console.WriteLine("{0}\t{1:0.00}", n, p * (1 - d));
}
NativeLibrary.Free(assetLib);
