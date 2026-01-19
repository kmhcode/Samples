
using DemoApp.Tools;

var builder = Host.CreateEmptyApplicationBuilder(null);
builder.Logging.ClearProviders();
builder.Services.AddMcpServer()
    .WithStdioServerTransport()
    .WithTools<Sales>();
var host = builder.Build();
Console.Error.WriteLine("Starting server...");
host.Run();
