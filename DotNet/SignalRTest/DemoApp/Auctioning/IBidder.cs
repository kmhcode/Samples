namespace DemoApp.Auctioning;

public interface IBidder
{
    Task BidAccepted(decimal newPrice);
}