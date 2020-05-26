plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-oauth2")
    implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure")
    implementation("org.springframework.hateoas:spring-hateoas")

    implementation("com.zaxxer:HikariCP")
    implementation("org.hibernate:hibernate-ehcache")
    implementation("mysql:mysql-connector-java")
    implementation("com.thedeanda:lorem:${property("loremVersion")}")
    implementation("javax.validation:validation-api:${property("validationApiVersion")}")
    implementation("commons-io:commons-io:${property("commonIoVersion")}")
    implementation("com.googlecode.log4jdbc:log4jdbc:${property("log4jdbcVersion")}")

    implementation("com.graphql-java:graphql-spring-boot-starter:${property("graphqlVersion")}")
    implementation("com.graphql-java:graphiql-spring-boot-starter:${property("graphqlVersion")}")
    implementation("com.graphql-java:graphql-java-tools:${property("graphqlJavaVersion")}")
    implementation("com.alibaba:fastjson:1.2.68")
    implementation(project(":utility"))
}
