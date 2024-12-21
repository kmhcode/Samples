using System.Runtime.InteropServices;

unsafe partial class Program
{

    [LibraryImport("primes", EntryPoint = "primes_fetch")]
    private static partial ulong FetchPrimes(ulong start, int count, delegate*<ulong, bool> selector, ulong* selected);

    private static bool IsFavorite(ulong num) => (num - 1) % 4 == 0;

    static void Main(string[] args)
    {
        ulong m = ulong.Parse(args[0]);
        int n = int.Parse(args[1]);
        Span<ulong> primes = n < 9 ? stackalloc ulong[n] : new ulong[n];
        fixed(ulong* selected = &primes[0])
        {
            if(n <= 5)
                FetchPrimes(m, n, null, selected);
            else
                FetchPrimes(m, n, &IsFavorite, selected);
        }
        foreach(ulong prime in primes)
            Console.WriteLine(prime);
    }
}
