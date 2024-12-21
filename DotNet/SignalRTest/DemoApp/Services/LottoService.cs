using DemoApp.Hubs;
using Microsoft.AspNetCore.SignalR;

namespace DemoApp.Services;

public class LottoService(IHubContext<GreeterHub> remote) : BackgroundService
{
    protected override async Task ExecuteAsync(CancellationToken stoppingToken)
    {
        while(!stoppingToken.IsCancellationRequested)
        {
            int winner = Random.Shared.Next(100000, 1000000);
            await remote.Clients.All.SendAsync("ReceiveWinner", winner.ToString(), stoppingToken);
            await Task.Delay(10000, stoppingToken);
        }
    }
}
