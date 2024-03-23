import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
    id("org.jetbrains.kotlinx.kover") version "0.7.4"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "project-report")
    apply(plugin = "org.jetbrains.kotlinx.kover")
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.2")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("commons-collections:commons-collections:3.2.2")
        implementation("commons-io:commons-io:2.15.0")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
        implementation("org.jetbrains.kotlinx:kover-gradle-plugin:0.7.4")

        implementation("org.hibernate:hibernate-validator:8.0.1.Final")
        implementation("org.apache.logging.log4j:log4j-core:2.14.1")
        implementation("com.sun.mail:jakarta.mail:1.6.7")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.boot:spring-boot-test-autoconfigure")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
        testImplementation("io.mockk:mockk:1.13.8")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("PASSED", "SKIPPED", "FAILED")
        }
    }
}

dependencies{
    kover(project("domain"))
    kover(project("app"))
}

val excludeCoverage = listOf(
    "**/*\$logger\$*.class",
    "com.fitness.projectluna.ProjectLunaApplication.kt",
    "com.fitness.projectluna.ProjectLunaApplicationKt",
    "com.fitness.projectluna.application.config.*",
    "com.fitness.projectluna.resource.repository.entity.*",
    "com.fitness.projectluna.model.*",
    "com.fitness.projectluna.exception.*",
    "com.fitness.projectluna.application.beans.*",
    "com.fitness.projectluna.application.core.*",
    "com.fitness.projectluna.application.dto.*",
    "com.fitness.projectluna.core.*"
)

koverReport {
    defaults{
        filters{
            excludes{
                classes(excludeCoverage)
            }
        }
        html{
            onCheck = true
            setReportDir(layout.buildDirectory.dir("reports/jacoco/test/html"))
        }
        xml{
            onCheck = true
            setReportFile(layout.buildDirectory.file("reports/jacoco/test/jacocoTestReport.xml"))
        }
    }
}

tasks.register("jacocoTestReport") {
    dependsOn("test", "koverHtmlReport", "koverXmlReport")
}

tasks {
    bootJar { enabled = false }
    bootRun { enabled = false }
}
