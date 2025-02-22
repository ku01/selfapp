package dev.ku01.finapp.service

import dev.ku01.finapp.converter.toAccount
import dev.ku01.finapp.dto.AccountDto
import dev.ku01.finapp.entity.Account
import dev.ku01.finapp.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(private val accountRepository: AccountRepository) {
    @Transactional
    fun postAccount(accountDto: AccountDto): Account {
        return accountRepository.save(accountDto.toAccount())
    }

    @Transactional(readOnly = true)
    fun getAccounts(): List<Account> {
        return accountRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun getAccount(id: Long): Account {
        return accountRepository.findById(id).get()
    }

    @Transactional
    fun deleteAccount(id: Long) {
        accountRepository.deleteById(id)
    }
}
