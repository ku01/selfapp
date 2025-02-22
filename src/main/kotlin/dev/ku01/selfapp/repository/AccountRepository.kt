package dev.ku01.selfapp.repository

import dev.ku01.selfapp.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long>
