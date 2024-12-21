using Microsoft.AspNetCore.SignalR;

namespace DemoApp.Hubs;

public class GreeterHub : Hub<IGreetingReceiver>
{
    public async Task<string> Greet(string name, int age)
    {
        await Clients.All.ReceiveGreeting(new Greeting());
        return age < 25 ? $"Hi {name}" : $"Hello {name}";
    }
}
