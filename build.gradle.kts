plugins {
	alias(libs.plugins.kotlin.jvm) apply false
	alias(libs.plugins.kotlin.spring) apply false
	alias(libs.plugins.kotlin.jpa) apply false
}

group = "dev.ku01.selfapp"

subprojects {
	repositories {
		mavenCentral()
	}
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
}
