package dev.ku01.selfapp.dto

import dev.ku01.selfapp.enum.Currency
import java.math.BigDecimal

data class AccountDto(
    var id: Long? = null,
    val name: String,
    val currency: Currency,
    val balance: BigDecimal,
)
