package org.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.UUID;

public class GreeterClient {

    public static void main(String[] args) {
        for(var i = 1; i <= 1000; i++) {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                    .usePlaintext()
                    .build();
            GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
            ExampleProto.HelloRequest request = ExampleProto.HelloRequest.newBuilder()
                    .setName(UUID.randomUUID().toString())
                    .build();
            ExampleProto.HelloReply response = stub.sayHello(request);
            System.out.println("Response: " + response.getMessage());
            channel.shutdown();
        }
    }
}
