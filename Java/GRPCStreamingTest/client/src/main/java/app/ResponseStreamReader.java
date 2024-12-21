package app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import io.grpc.stub.StreamObserver;

public class ResponseStreamReader<TMessage> implements StreamObserver<TMessage> {
    
    private final List<TMessage> messages = new ArrayList<>();
    private final CountDownLatch latch = new CountDownLatch(1);
    private volatile Throwable error = null;
    
    public void onNext(TMessage result) {
        messages.add(result);
    }

    public void onError(Throwable fault) {
        error = fault;
        latch.countDown();
    }

    public void onCompleted() {
        latch.countDown();
    }

    public Iterator<TMessage> readAll() throws InterruptedException {
        latch.await();
        if(error != null)
            throw new RuntimeException(error);
        return messages.iterator();
    }

    public TMessage readFirst() throws InterruptedException {
        return readAll().next();
    }

}
