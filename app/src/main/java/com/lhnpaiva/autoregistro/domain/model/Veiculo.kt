package com.lhnpaiva.autoregistro.domain.model

data class Veiculo(
    val id: Int,
    val nome: String,
    val modelo: String,
    val placa: String,
    val quilometragem: Int,
    val gastos: List<Gasto>
)