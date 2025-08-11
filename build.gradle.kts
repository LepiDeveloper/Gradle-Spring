plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    id("java")
    id("application")
}

application {
    mainClass = "be.kdg.programming3.spaceMissions.StartApplication"
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

tasks.named<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
    standardInput = System.`in`
}


group = "be.kdg.programing3_resit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.webjars:bootstrap:5.3.3")
    implementation ("org.webjars:webjars-locator:0.52")
    implementation ("org.webjars.npm:bootstrap-icons:1.11.3")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}