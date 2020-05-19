plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("org.springframework.boot:spring-boot-devtools")
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-logging")

//    implementation("org.springframework.cloud:spring-cloud-starter-config")
//    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-oauth2")
    implementation("org.springframework.hateoas:spring-hateoas:1.1.0.RELEASE")
    implementation("commons-io:commons-io:2.6")

//    implementation("com.zaxxer:HikariCP")
//    implementation("org.hibernate:hibernate-ehcache")
//    implementation("mysql:mysql-connector-java")
    implementation("com.thedeanda:lorem:${property("loremVersion")}")
    implementation("javax.validation:validation-api:${property("validationApiVersion")}")
//    implementation("com.googlecode.log4jdbc:${property("log4jdbcVersion")}")

    implementation(project(":utility"))
}

//springBoot {
//    mainClassName = "com.zipe.Application"
//}
