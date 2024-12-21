namespace DemoApp.Hubs;

public class Greeting
{
    private static int count = 0;

    public int Number { get; set; } = Interlocked.Increment(ref count);

    public DateTime Moment { get; set; } = DateTime.Now;
}
