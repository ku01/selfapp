package dev.ku01.selfapp.controller

import dev.ku01.selfapp.converter.toDto
import dev.ku01.selfapp.dto.AccountDto
import dev.ku01.selfapp.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(private val accountService: AccountService) {
    @PostMapping
    fun postAccount(@RequestBody accountDto: AccountDto): AccountDto {
        return accountService.postAccount(accountDto).toDto()
    }

    @GetMapping
    fun getAccounts(): List<AccountDto> {
        return accountService.getAccounts().map { it.toDto() }
    }

    @GetMapping("/{id}")
    fun getAccount(@PathVariable id: Long): AccountDto {
        return accountService.getAccount(id).toDto()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAccount(@PathVariable id: Long) {
        accountService.deleteAccount(id)
    }
}
