using Computation;
using Grpc.Core.Utils;
using Grpc.Net.Client;

using var channel = GrpcChannel.ForAddress("http://localhost:4040");
var remote = new ComputeSupport.ComputeSupportClient(channel);
var generateValuesRequest = new DataRange 
{
    Lower = 10,
    Upper = 100,
    Count = 5
};
using var generateValueResponse = remote.GenerateValues(generateValuesRequest);
using var combineValuesCall = remote.CombineValues();
await generateValueResponse.ResponseStream.ForEachAsync(async msg => 
{
    Console.WriteLine("{0:0.000}", msg.Value);
    await combineValuesCall.RequestStream.WriteAsync(msg);
});
await combineValuesCall.RequestStream.CompleteAsync();
var combineValuesResponse = await combineValuesCall.ResponseAsync;
Console.WriteLine("Result = {0:0.000}", combineValuesResponse.Value);
