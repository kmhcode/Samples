using DemoApp.Auctioning;

var builder = WebApplication.CreateBuilder(args);
builder.Services.AddSignalR();
builder.Services.AddSingleton<AuctionItem>();
builder.Services.AddHostedService<DeadlineTicker>();
var app = builder.Build();
app.UseFileServer();
app.MapHub<AuctionHub>("/auction");
app.Run();
