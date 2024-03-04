package dev.ku01.self.app.dto

import java.math.BigDecimal
import java.util.Currency

data class AccountDto(
    val id: Long,
    val amount: BigDecimal,
    val currency: String
)
