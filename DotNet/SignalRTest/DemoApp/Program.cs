using DemoApp.Hubs;
using DemoApp.Services;

var builder = WebApplication.CreateBuilder(args);
builder.Services.AddSignalR();
builder.Services.AddHostedService<LottoService>();
var app = builder.Build();
app.UseFileServer();
app.MapHub<GreeterHub>("/welcome");
app.Run();
