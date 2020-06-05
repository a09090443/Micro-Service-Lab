plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))

    implementation("org.springframework.cloud:spring-cloud-config-server")
}
