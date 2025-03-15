plugins {
	val kotlinVer = "1.9.25"
	kotlin("jvm") version kotlinVer apply false
	kotlin("plugin.spring") version kotlinVer apply false
	kotlin("plugin.jpa") version kotlinVer apply false
}

group = "dev.ku01"

subprojects {
	repositories {
		mavenCentral()
	}
}
