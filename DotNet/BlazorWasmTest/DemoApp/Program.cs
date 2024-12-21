using DemoApp.Client.Shared;
using DemoApp.Components;
using DemoApp.Data.Shopping;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorComponents()
    .AddInteractiveWebAssemblyComponents();
builder.Services.AddDbContext<ShopDbContext>(options => options.UseSqlite("Data Source=shop.db"));
var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseWebAssemblyDebugging();
}
else
{
    app.UseExceptionHandler("/Error", createScopeForErrors: true);
}


app.UseAntiforgery();

app.MapStaticAssets();
app.MapRazorComponents<App>()
    .AddInteractiveWebAssemblyRenderMode()
    .AddAdditionalAssemblies(typeof(DemoApp.Client._Imports).Assembly);

app.MapPost("/api/orders", async (CustomerLogin input, ShopDbContext shop) => 
{
    var customer = await shop.Customers.FindAsync(input.UserName);
    if(customer != null && customer.Password == input.Password)
    {
        var selection = from entry in shop.Orders
            where entry.CustomerId == input.UserName
            select new CustomerOrder
            (
                entry.ProductId,
                entry.Quantity,
                entry.OrderDate.ToString("yyyy-MM-dd")
            );
        return Results.Ok(await selection.ToArrayAsync());
    }
    else
    {
        return Results.Unauthorized();
    }
});

app.Run();
