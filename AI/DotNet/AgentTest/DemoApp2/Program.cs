using DemoApp.Helpers;
using DemoApp.Tools;
using Microsoft.Extensions.Configuration;

var settings = new ConfigurationBuilder()
    .SetBasePath(Environment.CurrentDirectory)
    .AddJsonFile("appsettings.json")
    .Build();
var ai = new OpenAIAdapter(settings);
var agent = ai.CreateAgentWithTools(
    """
    You are an inventory expert, you provide item related information from given tools.
    Provide only final result.
    """,
    new Shop()
);
var reply = await agent.RunAsync(args[0]);
Console.WriteLine(reply.Text);
