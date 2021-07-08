package br.com.zup.ed

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
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

    //escremos o objeto
    println(request)
    request.writeTo(FileOutputStream("funcionario-request.bin"))

    // lemos o objeto
    val request2  = FuncionarioRequest.newBuilder()
        .mergeFrom(FileInputStream("funcionario-request.bin"))

    request2.setCargo(Cargo.GERENTE)

    println(request2)
}