plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
    id("java-test-fixtures")
}

apply(plugin = "project-report")

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc:3.2.0")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.asyncer:r2dbc-mysql:1.0.5")
    implementation("commons-beanutils:commons-beanutils:1.9.4")
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.10")
    implementation("com.github.f4b6a3:tsid-creator:5.2.5")

    // #### RESILIENCE
    implementation("io.github.resilience4j:resilience4j-spring-boot3:2.1.0")
    implementation("io.github.resilience4j:resilience4j-kotlin:2.1.0")
    // ##

    // #### MESSAGE
    implementation("org.springframework.boot:spring-boot-starter-amqp")

    implementation("org.apache.commons:commons-text:1.11.0")

    runtimeOnly("mysql:mysql-connector-java:8.0.33")

    testRuntimeOnly("com.h2database:h2:2.2.224")
    testRuntimeOnly("io.r2dbc:r2dbc-h2:1.0.0.RELEASE")

    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.cloud:spring-cloud-contract-wiremock:4.1.0")

    testImplementation(testFixtures(project(":domain")))
    testFixturesImplementation(testFixtures(project(":domain")))
    testImplementation(testFixtures(project(":app")))
}

sourceSets {
    create("integrationTest") {
        compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
        runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
        resources.srcDir("src/integrationTest/resources")
    }
}

task<Test>("integrationTest") {
    environment("APPLICATION", "mback-ecommerce")
    description = "Runs the integration tests"
    group = "verification"
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    mustRunAfter(tasks["test"])
    useJUnitPlatform()
}

tasks.test {
    environment("APPLICATION", "mback-ecommerce")
}

tasks {
    bootJar {
        destinationDirectory.set(file("${rootProject.buildDir}/libs"))
    }
    bootRun {
        environment.putIfAbsent("APPLICATION", "mback-ecommerce")
    }
}