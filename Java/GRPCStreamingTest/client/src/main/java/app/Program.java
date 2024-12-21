package app;

import computation.ComputeSupportGrpc;
import computation.Computation.DataItem;
import computation.Computation.DataRange;
import io.grpc.ManagedChannelBuilder;

public class Program {
    
    public static void main(String[] args) throws Exception {
        var channel = ManagedChannelBuilder.forAddress("localhost", 4040)
            .usePlaintext()
            .build();
        var remote = ComputeSupportGrpc.newStub(channel);
        var generateValuesResponse = new ResponseStreamReader<DataItem>();
        var generateValuesRequest = DataRange.newBuilder()
            .setLower(10)
            .setUpper(100)
            .setCount(5)
            .build();
        remote.generateValues(generateValuesRequest, generateValuesResponse);
        var combineValuesResponse = new ResponseStreamReader<DataItem>();
        var combineValuesRequest = remote.combineValues(combineValuesResponse);
        generateValuesResponse.readAll().forEachRemaining(msg -> {
            System.out.printf("%.3f%n", msg.getValue());
            combineValuesRequest.onNext(msg);
        });
        combineValuesRequest.onCompleted();
        System.out.printf("Result = %.3f%n", combineValuesResponse.readFirst().getValue());
        channel.shutdown();
    }

}

