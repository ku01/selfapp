package dev.ku01.self.app.controller

import dev.ku01.self.app.dto.AccountDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/accounts")
class AccountController {
    @GetMapping
    fun accountList(): List<AccountDto> {
        return listOf(AccountDto(1, BigDecimal("1000.0"), "USD"))
    }
}
