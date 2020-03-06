plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework:spring-orm")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-oauth2")
    implementation("org.springframework.security:spring-security-core")
    implementation("org.springframework.security:spring-security-config")
    implementation("org.springframework.security:spring-security-web")

    implementation("com.zaxxer:HikariCP")
    implementation("org.hibernate:hibernate-ehcache")
    implementation("mysql:mysql-connector-java")
    implementation("com.thedeanda:lorem:${property("loremVersion")}")

    implementation(project(":utility"))
}

//springBoot {
//    mainClassName = "com.zipe.Application"
//}
