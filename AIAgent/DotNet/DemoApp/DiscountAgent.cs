using System.ComponentModel;
using Microsoft.SemanticKernel;

namespace DemoApp;

public class DiscountAgent
{
    [KernelFunction, Description("Gets percentage discount offered for an item")]
    public float GetBullDiscount([Description("Number of items")] int quantity, [Description("Name of item")] string item)
    {
        Console.WriteLine("Estimating discount percentage for {0}...", item);
        return quantity switch
        {
            > 1 and < 10 => 5,
            >= 10  => 7.5f,
            _ => 0
        };
    }
}
