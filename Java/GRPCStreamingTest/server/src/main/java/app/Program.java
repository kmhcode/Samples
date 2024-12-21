package app;

import app.services.ComputeSupportService;
import io.grpc.ServerBuilder;

public class Program {
    
    public static void main(String[] args) throws Exception {
        ServerBuilder.forPort(4040)
            .addService(new ComputeSupportService())
            .build()
            .start()
            .awaitTermination();
    }

}

