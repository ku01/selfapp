package dev.ku01.selfapp.converter

import dev.ku01.selfapp.test.account
import dev.ku01.selfapp.test.accountDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.testcontainers.shaded.org.apache.commons.lang3.RandomUtils

class AccountConvertersTest {
    @Test
    fun `accountDto to account`() {
        val accountDto = accountDto(id = RandomUtils.nextLong())
        with(accountDto.toEntity()) {
            assertNull(id)
            assertEquals(accountDto.name, name)
            assertEquals(accountDto.currency, currency)
            assertEquals(accountDto.balance, balance)
        }
    }

    @Test
    fun `account ot accountDto`() {
        val account = account(id = RandomUtils.nextLong())
        with(account.toDto()) {
            assertEquals(account.id, id)
            assertEquals(account.name, name)
            assertEquals(account.currency, currency)
            assertEquals(account.balance, balance)
        }
    }
}
