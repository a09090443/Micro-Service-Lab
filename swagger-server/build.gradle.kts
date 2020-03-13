plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.plugin:spring-plugin-core:1.2.0.RELEASE")

    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-config")

    implementation("io.springfox:springfox-swagger2:${property("swaggerVersion")}")
    implementation("io.springfox:springfox-swagger-ui:${property("swaggerVersion")}")
//    implementation("io.springfox:springfox-data-rest:2.9.2")
}
