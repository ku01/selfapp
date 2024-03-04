package dev.ku01.self.app.dto

import java.math.BigDecimal

data class AccountDto(
    val id: Long,
    val amount: BigDecimal,
    val currency: String
)
