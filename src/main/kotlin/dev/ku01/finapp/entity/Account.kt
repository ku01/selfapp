package dev.ku01.finapp.entity

import dev.ku01.finapp.enum.Currency
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
class Account(
    var name: String,
    @Enumerated(EnumType.STRING)
    var currency: Currency,
    var balance: BigDecimal,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator_id")
    @SequenceGenerator(name = "account_generator_id", sequenceName = "account_id_seq", initialValue = 1, allocationSize = 1)
    var id: Long? = null
}
