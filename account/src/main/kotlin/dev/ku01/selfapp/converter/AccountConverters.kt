package dev.ku01.selfapp.converter

import dev.ku01.selfapp.dto.AccountDto
import dev.ku01.selfapp.entity.Account

fun AccountDto.toEntity() = Account(
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
