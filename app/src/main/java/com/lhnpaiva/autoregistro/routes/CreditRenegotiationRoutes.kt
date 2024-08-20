package com.lhnpaiva.autoregistro.routes

enum class CreditRenegotiationRoutes(val routeValue: String) {
    HOME("home"),
    LOGIN("login");

    companion object {
        fun findByValue(value: String): CreditRenegotiationRoutes {
            return enumValues<CreditRenegotiationRoutes>().firstOrNull {
                it.routeValue.equals(
                    value,
                    true
                )
            } ?: HOME
        }
    }
}