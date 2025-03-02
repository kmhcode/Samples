using System.Runtime.InteropServices;

unsafe class Program
{
    [DllImport("native/libprimes.so", EntryPoint = "primes_fetch")]
    static extern void FetchPrimes(ulong start, int count, delegate*<ulong, bool> selector, ulong* selected);

    static bool IsFavorite(ulong num) => (num - 1) % 4 == 0;

    static void Main(string[] args)
    {
        ulong m = ulong.Parse(args[0]);
        if(args.Length == 1)
        {
            ulong prime = 0;
            FetchPrimes(m, 1, null, &prime);
            Console.WriteLine(prime);
        }
        else
        {
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
}
