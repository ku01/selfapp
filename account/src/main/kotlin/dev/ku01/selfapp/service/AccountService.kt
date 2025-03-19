package dev.ku01.selfapp.service

import dev.ku01.selfapp.entity.Account
import dev.ku01.selfapp.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(private val accountRepository: AccountRepository) {
    @Transactional
    fun postAccount(account: Account): Account {
        return accountRepository.save(account)
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
