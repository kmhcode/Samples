package app.services;

import java.util.Random;

import computation.Computation.DataItem;
import computation.Computation.DataRange;
import computation.ComputeSupportGrpc.ComputeSupportImplBase;
import io.grpc.stub.StreamObserver;

public class ComputeSupportService extends ComputeSupportImplBase {
    
    private static final Random rdm = new Random();

    @Override
    public void generateValues(DataRange request, StreamObserver<DataItem> responseObserver) {
		double min = request.getLower();
		double max = request.getUpper();
        for(int i = 0; i < request.getCount(); ++i){
            double sample = rdm.nextDouble(min, max);
            responseObserver.onNext(DataItem.newBuilder().setValue(sample).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<DataItem> combineValues(StreamObserver<DataItem> responseObserver) {
        return new StreamObserver<DataItem>() {
            
            double sum = 0;

            @Override
            public void onCompleted() {
                double result = Math.sqrt(sum);
                responseObserver.onNext(DataItem.newBuilder().setValue(result).build());
                responseObserver.onCompleted();
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onNext(DataItem entry) {
                double value = entry.getValue();
                sum += value * value;
            }
        };
    }
    
}
