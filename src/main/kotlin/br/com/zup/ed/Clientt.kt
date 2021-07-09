package br.com.zup.ed

import io.grpc.ManagedChannelBuilder

fun main () {
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build()


    val request = FuncionarioRequest.newBuilder()
        .setName("William")
        .setCpf("000.000.000-00")
        .setAtivo(true)
        .setCargo(Cargo.DEV)
        .setSalario(2500.00)
        .addEndereco(
            FuncionarioRequest.Endereco.newBuilder()
                .setLocradouro("Rua tal")
                .setCep("00000-000")
                .setComplemento("Casa")
                .build())
        .build()

    val client = ProtobufServiceGrpc.newBlockingStub(channel)
    val response = client.cadastra(request)

    println(response)

}