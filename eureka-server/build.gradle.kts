plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}
