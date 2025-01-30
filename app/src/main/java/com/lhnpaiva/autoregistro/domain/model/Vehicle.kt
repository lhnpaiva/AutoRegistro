package com.lhnpaiva.autoregistro.domain.model

data class Vehicle(
    val id: Int,
    val name: String,
    val model: String,
    val plate: String,
    val km: Int,
    val expenses: List<Expense>
)