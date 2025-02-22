package dev.ku01.finapp.converter

import dev.ku01.finapp.dto.AccountDto
import dev.ku01.finapp.entity.Account

fun AccountDto.toAccount() = Account(
    name = name,
    currency = currency,
    balance = balance,
)

fun Account.toDto() = AccountDto(
    id = id,
    name = name,
    currency = currency,
    balance = balance,
)
