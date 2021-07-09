package br.com.zup.ed

import com.google.protobuf.Timestamp
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneId

fun main() {
    val server = ServerBuilder
        .forPort(50051)
        .addService(FuncionarioEndPoint())
        .build()

    server.start()
    server.awaitTermination()
}

class FuncionarioEndPoint : ProtobufServiceGrpc.ProtobufServiceImplBase() {

    override fun cadastra(request: FuncionarioRequest, responseObserver: StreamObserver<FuncionarioResponse>?) {
        var name: String? = request.name
        if (!request.hasField(FuncionarioRequest.getDescriptor().findFieldByName("name"))) {
            name = "[????]"
        }
        val nome = request.name
        val instant = LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()
        val criadoEm = Timestamp.newBuilder()
            .setSeconds(instant.epochSecond)
            .setNanos(instant.nano)
            .build()

        val response = FuncionarioResponse.newBuilder()
            .setNome(nome)
            .setCriadoEm(criadoEm)
            .build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }

}