namespace DemoApp.Auctioning;

public class AuctionItem
{
    public decimal CurrentPrice { get; private set; } = 50;

    public bool UpdatePrice(decimal newValue)
    {
        lock(this)
        {
            if(newValue >= CurrentPrice + 10)
            {
                CurrentPrice = newValue;
                return true;
            }
            return false;
        }
    }
}