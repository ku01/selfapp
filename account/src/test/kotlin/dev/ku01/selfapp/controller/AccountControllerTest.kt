package dev.ku01.selfapp.controller

import dev.ku01.selfapp.entity.Account
import dev.ku01.selfapp.enum.Currency
import dev.ku01.selfapp.service.AccountService
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
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
                this.id = 1000
            }
        }

        mockMvc.perform(
            post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(accountDto())
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").value(1000))
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
}
