using Microsoft.AspNetCore.SignalR;

namespace DemoApp.Auctioning;

public class DeadlineTicker(IHubContext<AuctionHub> hub) : BackgroundService
{
    private TimeSpan remaining = TimeSpan.FromDays(3);

    protected override async Task ExecuteAsync(CancellationToken stoppingToken)
    {
        while(!stoppingToken.IsCancellationRequested)
        {
            remaining -= TimeSpan.FromSeconds(1);
            await hub.Clients.All.SendAsync("TimeRemaining", remaining, stoppingToken);
            await Task.Delay(1000);
        }
    }
}
