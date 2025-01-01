using Microsoft.AspNetCore.Server.Kestrel.Core;
using ServerApp.Services;

var builder = WebApplication.CreateBuilder(args);
builder.Services.AddGrpc();
builder.WebHost.ConfigureKestrel(options => 
    options.ConfigureEndpointDefaults(settings => settings.Protocols = HttpProtocols.Http2)
);
var app = builder.Build();
app.MapGrpcService<ComputeSupportService>();
app.Run("http://localhost:4040");
