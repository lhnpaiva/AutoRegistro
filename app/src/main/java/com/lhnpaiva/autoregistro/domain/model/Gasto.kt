package com.lhnpaiva.autoregistro.domain.model

data class Gasto(
    val id: Int,
    val descricao: String,
    val kmPrevisto: Int,
    val urgente: Boolean
)