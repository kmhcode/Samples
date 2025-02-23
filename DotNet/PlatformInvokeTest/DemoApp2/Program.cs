using System.Runtime.InteropServices;

unsafe partial class Program
{
    //[DllImport("../NativeLib/libprimes.so", EntryPoint = "primes_fetch")]
    //private static extern ulong FetchPrimes(ulong start, int count, delegate*<ulong, bool> selector, ulong* selected);

    [LibraryImport("../NativeLib/libprimes.so", EntryPoint = "primes_fetch")]
    private static partial ulong FetchPrimes(ulong start, int count, delegate*<ulong, bool> selector, ulong* selected);

    static bool IsFavorite(ulong num) => (num - 1) % 4 == 0;

    static void Main(string[] args)
    {
        ulong m = ulong.Parse(args[0]);
        int n = int.Parse(args[1]);
        Span<ulong> primes = n > 8 ? new ulong[n] : stackalloc ulong[n];
        fixed(ulong* store = &primes[0])
        {
            FetchPrimes(m, n, &IsFavorite, store);
        }
        foreach(ulong prime in primes)
            Console.WriteLine(prime);
    }
}