using Data.Shopping;
using Microsoft.AspNetCore.Components;
using Microsoft.EntityFrameworkCore;

namespace DemoApp.Components.Pages;

partial class Home
{
    [Inject]
    public ShopDbContext Shop { get; set; } = default!;

    public List<Product>? Inventory { get; set; }

    protected override async Task OnInitializedAsync()
    {
        Inventory = await Shop.Products.ToListAsync();
    }

    private async Task SaveProduct(Product input)
    {
        Shop.Update(input);
        await Shop.SaveChangesAsync();
    }
}
