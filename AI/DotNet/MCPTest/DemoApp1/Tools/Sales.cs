using System.ComponentModel;
using DemoApp.Models;
using ModelContextProtocol.Server;

namespace DemoApp.Tools;

[McpServerToolType]
public class Sales
{
    private readonly Inventory model = new Inventory();

    [McpServerTool]
    [Description("Gets total price in purchase of an available item")]
    public double? QuotePrice([Description("Singularized name of item in lower-case")] string item, [Description("Number of items")] int quantity)
    {
        var info = model.Store.GetValueOrDefault(item);
        if(info != null)
        {
            double discount = quantity < 10 ? 0 : 0.05;
            return quantity * info.UnitPrice * (1 - discount);
        }
        return null;
    }

}