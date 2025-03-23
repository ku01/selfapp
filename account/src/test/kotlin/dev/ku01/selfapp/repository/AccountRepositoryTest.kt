package dev.ku01.selfapp.repository

import dev.ku01.selfapp.test.account
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.transaction.annotation.Transactional

class AccountRepositoryTest : BaseRepositoryTest() {
    @Test
    @Transactional
    fun `save and find`() {
        val account = account()
        val result = accountRepository.save(account)
        assertThat(result).isSameAs(account)
    }
}
