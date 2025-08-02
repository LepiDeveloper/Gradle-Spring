plugins {
    id("java")
    id("application")
}

application {
    mainClass = "be.kdg.programming3.spaceMissions.StartApplication"
}

group = "be.kdg.programing3_resit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}