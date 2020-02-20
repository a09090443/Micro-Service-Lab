import org.gradle.api.tasks.bundling.Jar
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    application
    kotlin("jvm")
}

application {
    mainClassName = "Main"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

/**
 * org.springframework.boot:spring-boot-gradle-plugin
 * because above module which is enabled on BootJar task
 */
tasks {
    withType<BootJar> {
        enabled = false
    }
    withType<Jar> {
        enabled = true
    }
}
