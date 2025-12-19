using DemoApp;
using Microsoft.SemanticKernel;
using Microsoft.SemanticKernel.ChatCompletion;
using Microsoft.SemanticKernel.Connectors.OpenAI;

var builder = Kernel.CreateBuilder();
builder.AddOpenAIChatCompletion(
    modelId: "default-llm",
    endpoint: new Uri("http://iitdac.met.edu/openai/"),
    apiKey: "not-required"
);
builder.Plugins.AddFromObject(new DiscountAgent());
var kernel = builder.Build();
var executionSettings = new OpenAIPromptExecutionSettings
{
    ToolCallBehavior = ToolCallBehavior.EnableKernelFunctions,
    Temperature = 0 //range: 0.0(pure logic) - 1.0(high creativity)
};
var chatHistory = new ChatHistory(args[0], AuthorRole.User);
var chatService = kernel.GetRequiredService<IChatCompletionService>();
var messageContent = await chatService.GetChatMessageContentAsync(chatHistory, executionSettings, kernel);
var functionContent = messageContent.Items.OfType<FunctionCallContent>().FirstOrDefault();
if(functionContent != null)
{
    var reply = await functionContent.InvokeAsync(kernel);
    Console.WriteLine(reply.Result);
}

//dotnet run "What will be the discount for 12 apples"
