plugins {
    kotlin("jvm") version "1.7.20"
}

repositories {
    mavenCentral()
}

tasks {
    wrapper {
        gradleVersion = "7.5.1"
    }
}
