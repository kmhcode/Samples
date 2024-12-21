using System.Runtime.InteropServices;

class Program
{
    [DllImport("native/libdijkstra.so", EntryPoint = "GCD")]
    private extern static long GreatestDivisior(long first, long second);

    static void Main(string[] args)
    {
        long m = long.Parse(args[0]);
        long n = long.Parse(args[1]);
        Console.WriteLine("G.C.D = {0}", GreatestDivisior(m, n));
    }
}
