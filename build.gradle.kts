plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.github.mikybars"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	compileOnly("org.projectlombok:lombok")
	implementation("io.github.wimdeblauwe:error-handling-spring-boot-starter:4.5.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.approvaltests:approvaltests:24.19.0")
	testImplementation("com.google.code.gson:gson:2.12.1")
	testImplementation("com.tngtech.archunit:archunit-junit5:1.4.0")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaCompile> {
	options.compilerArgs.addAll(listOf(
		"-Amapstruct.defaultComponentModel=spring"
	))
}

tasks.withType<Test> {
	useJUnitPlatform()
}
