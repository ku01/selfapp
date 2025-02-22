package dev.ku01.finapp.dto

import dev.ku01.finapp.enum.Currency
import java.math.BigDecimal

data class AccountDto(
    var id: Long? = null,
    val name: String,
    val currency: Currency,
    val balance: BigDecimal,
)
