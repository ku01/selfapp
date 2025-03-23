package dev.ku01.selfapp.service

import dev.ku01.selfapp.entity.Account
import dev.ku01.selfapp.repository.AccountRepository
import dev.ku01.selfapp.test.account
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*
import kotlin.test.assertEquals

class AccountServiceTest : BaseMockTest() {
    @InjectMocks
    private lateinit var accountService: AccountService

    @Mock
    private lateinit var accountRepository: AccountRepository

    @Test
    fun `postAccount - happy path`() {
        whenever(accountRepository.save(any<Account>())).thenAnswer {
            (it.arguments[0] as Account).apply { id = 1 }
        }

        val account = account(id = null)
        val result = accountService.postAccount(account)
        assertThat(result).isSameAs(account)
        assertEquals(1, account.id)
    }

    @Test
    fun `getAccounts - happy path`() {
        val accounts = listOf(account(id = 1), account(id = 2))
        whenever(accountRepository.findAll()).thenReturn(accounts)
        val result = accountService.getAccounts()
        assertThat(result).hasSize(2)
        assertThat(result).containsExactlyInAnyOrder(*accounts.toTypedArray())
    }

    @Test
    fun `getAccount - happy path`() {
        val account = account(id = 1)
        whenever(accountRepository.findById(1)).thenReturn(Optional.of(account))
        val result = accountService.getAccount(1)
        assertThat(result).isSameAs(account)
    }

    @Test
    fun `deleteAccount - happy path`() {
        accountService.deleteAccount(1)
        verify(accountRepository).deleteById(1)
    }
}
