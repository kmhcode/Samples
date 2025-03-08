using System.Runtime.InteropServices;

unsafe class Program
{

    [DllImport("native/libprimes", EntryPoint = "primes_fetch")]
    private static extern void FetchPrimes(ulong start, int count, ulong* selected, delegate*<ulong, bool> selector);

    private static bool IsFavorite(ulong num) => (num - 1) % 4 == 0;

    static void Main(string[] args)
    {
        ulong m = ulong.Parse(args[0]);
        if(args.Length < 2)
        {
            FetchPrimes(m, 1, &m, null);
            Console.WriteLine(m);
        }
        else
        {
            int n = int.Parse(args[1]);
            ulong[] primes = new ulong[n];
            fixed(ulong* first = &primes[0])
            {
                FetchPrimes(m, n, first, &IsFavorite);
            }
            foreach(ulong prime in primes)
                Console.WriteLine(prime);
        }
    }
}
