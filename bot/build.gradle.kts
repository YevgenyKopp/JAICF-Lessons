plugins {
    application

    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.5.20"

    id("com.justai.jaicf.jaicp-build-plugin") version "0.1.1"

    id("org.springframework.boot") version "2.6.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.5.20"
}

group = "com.justai.lessons"
version = "1.0-SNAPSHOT"

val jaicf = "1.2.4"
val logback = "1.2.10"
val ktor = "1.5.1"
val mongo = "3.1.8"
val springCloud = "3.1.2"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.data:spring-data-mongodb:$mongo")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("ch.qos.logback:logback-classic:$logback")

    implementation("com.just-ai.jaicf:core:$jaicf")
    implementation("com.just-ai.jaicf:jaicp:$jaicf")
    implementation("com.just-ai.jaicf:caila:$jaicf")
    implementation("com.just-ai.jaicf:telegram:$jaicf")
    implementation("com.just-ai.jaicf:mongo:$jaicf")

    implementation("io.ktor:ktor-client-serialization:$ktor")
    implementation("io.ktor:ktor-client-apache:$ktor")
    implementation("io.ktor:ktor-client-features:$ktor")
    implementation("io.ktor:ktor-client-jackson:$ktor")

    implementation("org.apache.httpcomponents:httpclient:4.5.13")

    implementation("javax.servlet:javax.servlet-api:3.1.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.6.10")

    implementation("org.springframework.cloud:spring-cloud-starter-config:$springCloud")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:$springCloud")

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks {
    test {
        useJUnitPlatform()
    }
}