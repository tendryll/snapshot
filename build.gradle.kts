plugins {
    java
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "io.aleksandr.labs"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-aop:3.1.0")
    implementation("io.github.resilience4j:resilience4j-spring-boot2:2.0.2")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.1.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation ("com.github.tomakehurst:wiremock-jre8:2.35.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
