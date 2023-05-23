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
    implementation("com.apicatalog:titanium-json-ld:1.3.2")
    implementation("org.glassfish:jakarta.json:2.0.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.cucumber:cucumber-java:7.12.0")
    testImplementation("io.cucumber:cucumber-junit:7.12.0")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.12.0")
    testImplementation("io.cucumber:cucumber-spring:7.12.0")
    testImplementation("org.junit.platform:junit-platform-suite:1.10.0-M1")
    testImplementation("org.skyscreamer:jsonassert:1.5.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}
