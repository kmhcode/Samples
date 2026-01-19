using DemoApp.Helpers;
using Microsoft.Extensions.Configuration;

var settings = new ConfigurationBuilder()
    .SetBasePath(Environment.CurrentDirectory)
    .AddJsonFile("appsettings.json")
    .Build();
var ai = new OpenAIAdapter(settings);
var agent = ai.CreateAgentWithData(
    """
    You are an inventory expert, you provide item related information from given data.
    Respond in plain text without markdowns.
    """,
    File.ReadAllText("medstore.csv")
);
var reply = await agent.RunAsync(args[0]);
Console.WriteLine(reply.Text);
