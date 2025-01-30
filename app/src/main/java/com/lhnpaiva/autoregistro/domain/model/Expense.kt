package com.lhnpaiva.autoregistro.domain.model

data class Expense(
    val id: Int,
    val description: String,
    val predictedKm: Int,
    val isUrgent: Boolean
)