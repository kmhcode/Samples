namespace DemoApp.Hubs;

public interface IGreetingReceiver
{
    Task ReceiveGreeting(Greeting signal);
}