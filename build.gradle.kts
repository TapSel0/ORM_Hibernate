plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.hibernate:hibernate-core:5.4.32.Final")
    implementation("org.springframework:spring-context:5.3.8")
    implementation("javax.persistence:javax.persistence-api:2.2")
    implementation("mysql:mysql-connector-java:8.0.32")
}

tasks.test {
    useJUnitPlatform()
}