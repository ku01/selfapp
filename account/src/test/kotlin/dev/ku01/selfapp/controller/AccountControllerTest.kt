package dev.ku01.selfapp.controller

import dev.ku01.selfapp.entity.Account
import dev.ku01.selfapp.enum.Currency
import dev.ku01.selfapp.service.AccountService
import dev.ku01.selfapp.test.account
import dev.ku01.selfapp.test.accountDto
import dev.ku01.selfapp.test.content
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import kotlin.test.assertEquals

@WebMvcTest(controllers = [AccountController::class])
class AccountControllerTest : BaseControllerTest() {
    @MockitoBean
    private lateinit var accountService: AccountService

    @Test
    fun postAccount() {
        whenever(accountService.postAccount(any())).thenAnswer {
            (it.arguments[0] as Account).apply {
                this.id = 1
            }
        }

        mockMvc.perform(
            post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(accountDto())
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.size()").value(4))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Cash"))
            .andExpect(jsonPath("$.currency").value("PLN"))
            .andExpect(jsonPath("$.balance").value(1200))

        argumentCaptor<Account> {
            verify(accountService).postAccount(capture())
            assertEquals("Cash", firstValue.name)
            assertEquals(Currency.PLN, firstValue.currency)
            assertEquals(BigDecimal(1200), firstValue.balance)
        }
    }

    @Test
    fun getAccounts() {
        whenever(accountService.getAccounts()).thenReturn(listOf(
            account(id = 1),
            account(id = 2, name = "Favourite bank account", currency = Currency.USD, balance = BigDecimal(3115.23)),
        ))

        mockMvc.perform(
            get("/accounts")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].size()").value(4))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("Cash"))
            .andExpect(jsonPath("$[0].currency").value("PLN"))
            .andExpect(jsonPath("$[0].balance").value(1200))
            .andExpect(jsonPath("$[1].size()").value(4))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].name").value("Favourite bank account"))
            .andExpect(jsonPath("$[1].currency").value("USD"))
            .andExpect(jsonPath("$[1].balance").value(3115.23))
    }

    @Test
    fun getAccount() {
        whenever(accountService.getAccount(1)).thenReturn(account(id = 1))

        mockMvc.perform(
            get("/accounts/1")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(4))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Cash"))
            .andExpect(jsonPath("$.currency").value("PLN"))
            .andExpect(jsonPath("$.balance").value(1200))
    }

    @Test
    fun deleteAccount() {
        mockMvc.perform(
            delete("/accounts/1")
        )
            .andExpect(status().isNoContent)
    }
}
