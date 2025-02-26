using System.Runtime.InteropServices;

double cost = double.Parse(args[0]);
int life = int.Parse(args[1]);
nint assetLib = NativeLibrary.Load(args[2]);
nint depreciationPtr = NativeLibrary.GetExport(assetLib, "depreciation");
var depreciationStub = Marshal.GetDelegateForFunctionPointer<DepreciationFunc>(depreciationPtr);
for(int after = 1; after < life; ++after)
{
    double d = depreciationStub.Invoke(life, after);
    Console.WriteLine("{0}\t{1:0.00}", after, cost * (1 - d));
}
NativeLibrary.Free(assetLib);
