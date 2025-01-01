using Microsoft.AspNetCore.SignalR;

namespace DemoApp.Auctioning;

public class AuctionHub(AuctionItem item) : Hub<IBidder>
{
    public async ValueTask<decimal> AcceptBid(decimal bidPrice)
    {
        if(bidPrice > 0 && item.UpdatePrice(bidPrice))
            await Clients.Others.BidAccepted(bidPrice);
        return item.CurrentPrice;
    }
}