using System.ComponentModel;
using DemoApp.Models;

namespace DemoApp.Tools;

public class Shop
{
    private readonly Inventory model = new ();

    [Description("Gets unit-price and stock-level of an item.")]
    public ItemInfo? FetchItem([Description("Singularized name of the item.")] string item)
    {
        return model.Store.GetValueOrDefault(item.ToLower());
    }

    [Description("Gets percentage discount for bulk purchase.")]
    public static float OfferDiscount([Description("Number of items")] int quantity)
    {
        if(quantity >= 20)
            return 7.5f;
        if(quantity >= 10)
            return 5.0f;
        return 0;
    }
}