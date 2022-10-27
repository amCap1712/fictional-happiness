plugins {
    kotlin("jvm") version "1.7.20"
    application
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation("org.jgrapht:jgrapht-core:1.5.1")
}

tasks {
    wrapper {
        gradleVersion = "7.5.1"
    }
}
