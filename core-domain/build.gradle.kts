plugins {
    kotlin("jvm")
}

group = "com.tommy"
version = "0.0.1-SNAPSHOT"

val logbackVersion: String by project
val slf4jVersion: String by project

dependencies {
    implementation("ch.qos.logback:logback-classic:${logbackVersion}")
    implementation("org.slf4j:slf4j-api:${slf4jVersion}")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
