using Computation;
using Grpc.Core;

namespace ServerApp.Services;

public class ComputeSupportService : ComputeSupport.ComputeSupportBase
{
    public override async Task GenerateValues(DataRange request, IServerStreamWriter<DataItem> responseStream, ServerCallContext context)
    {
        double min = request.Lower;
        double max = request.Upper;
        for(int i = 0; i < request.Count; ++i)
        {
            double sample = min + (max - min) * Random.Shared.NextDouble();
            await responseStream.WriteAsync(new DataItem { Value = sample });
        }
    }

    public override async Task<DataItem> CombineValues(IAsyncStreamReader<DataItem> requestStream, ServerCallContext context)
    {
        double sum = 0;
        while(await requestStream.MoveNext())
        {
            double value = requestStream.Current.Value;
            sum += value * value;
        }
        return new DataItem { Value = Math.Sqrt(sum) };
    }
}
