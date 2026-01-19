using System.ClientModel;
using System.ComponentModel;
using Microsoft.Agents.AI;
using Microsoft.Extensions.AI;
using Microsoft.Extensions.Configuration;
using OpenAI;

namespace DemoApp.Helpers;

public class OpenAIAdapter(IConfiguration config)
{
    private readonly ChatClientBuilder clientBuilder = new OpenAIClient(
        new ApiKeyCredential(config["OpenAI:Provider:ApiKey"] ?? "none"), 
        new OpenAIClientOptions { Endpoint = new Uri(config["OpenAI:Provider:BaseUrl"] ?? "http://localhost:11434/v1") }
    )
    .GetChatClient(config["OpenAI:Chat:Model"] ?? "")
    .AsIChatClient()
    .AsBuilder();

    private readonly ChatOptions clientOptions = new()
    {
        Temperature = float.Parse(config["OpenAI:Chat:Temperature"] ?? "0.7")        
    };

    public ChatClientAgent CreateAgentWithData(string message, string source)
    {
        return clientBuilder.Build()
            .CreateAIAgent(new ChatClientAgentOptions
            {
                ChatOptions = new ChatOptions
                {
                    Instructions = message + "\n\n" + source,
                    Temperature = clientOptions.Temperature
                }
            });
    }

    public ChatClientAgent CreateAgentWithTools(string message, object source)
    {
        var functions = source.GetType().GetMethods()
            .Where(m => m.IsDefined(typeof(DescriptionAttribute), true))
            .Select(m => AIFunctionFactory.Create(m, source));
        return clientBuilder.UseFunctionInvocation().Build()
            .CreateAIAgent(new ChatClientAgentOptions
            {
                ChatOptions = new ChatOptions
                {

                    Instructions = message,
                    Tools = [.. functions],
                    Temperature = clientOptions.Temperature
                }                
            });
    }

}
