package dev.ku01.finapp.repository

import dev.ku01.finapp.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long>
