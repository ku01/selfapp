package dev.ku01.selfapp.repository

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.transaction.support.TransactionTemplate
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
abstract class BaseRepositoryTest {
    @Autowired
    protected lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    protected lateinit var txTemplate: TransactionTemplate

    @Autowired
    protected lateinit var accountRepository: AccountRepository

    companion object {
        @JvmStatic
        var postgres: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:17.4-alpine")

        @JvmStatic
        @DynamicPropertySource
        fun configureProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
        }

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            postgres.start()
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            postgres.stop()
        }
    }

    @BeforeEach
    fun setUp() {
        val dollar = '$'
        txTemplate.execute {
            jdbcTemplate.execute(
                """
                        DO
                        ${dollar}func$dollar
                        BEGIN
                           EXECUTE (SELECT 'TRUNCATE TABLE ' || string_agg(oid::regclass::text, ', ') || ' CASCADE'
                            FROM pg_catalog.pg_class
                            WHERE  relkind = 'r'  -- only tables
                            AND    relnamespace = 'public'::regnamespace
                            AND    relname != 'databasechangelog'
                            AND    relname != 'databasechangeloglock'
                           );
                        END
                        ${dollar}func$dollar;
                    """
            )
        }
    }
}
