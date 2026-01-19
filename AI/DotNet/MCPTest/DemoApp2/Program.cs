var builder = WebApplication.CreateSlimBuilder(args);
builder.Services.AddMcpServer()
    .WithHttpTransport()
    .WithToolsFromAssembly();
var app = builder.Build();
app.MapMcp("/mcp");
app.Run();
