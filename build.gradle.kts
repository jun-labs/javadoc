import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.jetbrains.dokka")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // SpringBoot
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.mockito")
    }
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.dokkaHtml {
    outputDirectory.set(layout.buildDirectory.dir("dokka").get().asFile)
}

tasks.dokkaHtml.configure {
    dokkaSourceSets {
        named("main") {
            perPackageOption {
                matchingRegex.set(".*kotlin/enums/.*")
                suppress.set(true)
            }
        }
    }
}

tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions).apply {
        encoding = "UTF-8"
        addStringOption("Xdoclint:none", "-quiet")
        addStringOption("html5", "-quiet")
    }

    // https://github.com/gradle/gradle/issues/19726
    val directories = sourceSets
        .main
        .get()
        .java
        .sourceDirectories
        .joinToString(":")
    (options as CoreJavadocOptions).addStringOption("-source-path", directories)
    exclude("**/response/**", "**/request/**")
}

tasks.dokkaHtml.configure {
    outputDirectory.set(layout.buildDirectory.dir("docs/dokka").get().asFile)
    dokkaSourceSets {
        named("main") {
            perPackageOption {
                matchingRegex.set(".*request.*")
                suppress.set(true)
            }
            perPackageOption {
                matchingRegex.set(".*response.*")
                suppress.set(true)
            }
        }
    }

}

configurations.matching { it.name.startsWith("dokka") }.configureEach {
    resolutionStrategy.eachDependency {
        if (requested.group.startsWith("com.fasterxml.jackson")) {
            useVersion("2.15.3")
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
