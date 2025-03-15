package dev.ku01.selfapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SelfappApplication

fun main(args: Array<String>) {
	runApplication<SelfappApplication>(*args)
}
