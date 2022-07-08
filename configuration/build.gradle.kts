plugins {
    application

    kotlin("jvm") version "1.5.20"
    kotlin("plugin.spring") version "1.5.20"

    id("org.springframework.boot") version "2.6.4"

    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

version = "0.0.1"

application {
    mainClass.set("com.justai.lessons.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.springframework.cloud:spring-cloud-config-server:3.1.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.withType(org.springframework.boot.gradle.tasks.bundling.BootJar::class.java).configureEach {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set("com.justai.lessons.ApplicationKt")
    archiveFileName.set("app-all.jar")
}