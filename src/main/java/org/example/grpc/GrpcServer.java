package org.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class GrpcServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(50051)
                .addService(new GreeterImpl())
                .build()
                .start();

        System.out.println("Server started, listening on 50051");

        server.awaitTermination();
    }

    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        @Override
        public void sayHello(ExampleProto.HelloRequest request, StreamObserver<ExampleProto.HelloReply> responseObserver) {
            ExampleProto.HelloReply reply = ExampleProto.HelloReply.newBuilder()
                    .setMessage("Hello " + request.getName())
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
