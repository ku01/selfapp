package dev.ku01.selfapp.test

import dev.ku01.selfapp.dto.AccountDto
import dev.ku01.selfapp.entity.Account
import dev.ku01.selfapp.enum.Currency
import java.math.BigDecimal

fun account(
    id: Long? = null,
    name: String = "Cash",
    currency: Currency = Currency.PLN,
    balance: BigDecimal = BigDecimal("1200"),
) = Account(
    name = name,
    currency = currency,
    balance = balance,
).apply {
    this.id = id
}

fun accountDto(
    id: Long? = null,
    name: String = "Cash",
    currency: Currency = Currency.PLN,
    balance: BigDecimal = BigDecimal("1200"),
) = AccountDto(
    id = id,
    name = name,
    currency = currency,
    balance = balance,
)
