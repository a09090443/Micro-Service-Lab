import org.gradle.api.tasks.bundling.Jar
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm")
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    api(kotlin("reflect"))

    api("com.fasterxml.jackson.module:jackson-module-kotlin")

    api("org.springframework.boot:spring-boot-starter-tomcat")
    api("org.springframework:spring-orm")
    api("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.boot:spring-boot-starter-actuator")
    api("org.springframework.boot:spring-boot-starter-thymeleaf")
    api("org.springframework.boot:spring-boot-starter-data-redis")
    api("org.springframework.boot:spring-boot-devtools")
    api("org.springframework.boot:spring-boot-starter-logging")

    api("org.springframework.cloud:spring-cloud-starter-config")
    api("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    api("org.springframework.cloud:spring-cloud-starter-oauth2")

    api("org.springframework.security:spring-security-core")
    api("org.springframework.security:spring-security-config")
    api("org.springframework.security:spring-security-web")

    api("com.graphql-java:graphql-spring-boot-starter:${property("graphqlVersion")}")
    api("com.graphql-java:graphiql-spring-boot-starter:${property("graphqlVersion")}")
    api("com.graphql-java:graphql-java-tools:${property("graphqlJavaVersion")}")
    api("com.alibaba:fastjson:1.2.68")

    api("com.zaxxer:HikariCP")
    api("org.hibernate:hibernate-ehcache")
    api("mysql:mysql-connector-java")
    api("com.thedeanda:lorem:${property("loremVersion")}")
    api("javax.validation:validation-api:${property("validationApiVersion")}")
    api("commons-io:commons-io:${property("commonIoVersion")}")
    api("com.googlecode.log4jdbc:log4jdbc:${property("log4jdbcVersion")}")

    api("org.slf4j:slf4j-api:${property("slf4jApiVersion")}")

    implementation("commons-io:commons-io:${property("commonIoVersion")}")
    implementation("com.googlecode.log4jdbc:log4jdbc:${property("log4jdbcVersion")}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

/**
 * org.springframework.boot:spring-boot-gradle-plugin
 * because above module which is enabled on BootJar task
 */
tasks {
//    withType<BootJar> {
//        enabled = false
//    }
    withType<Jar> {
        enabled = true
    }
}
