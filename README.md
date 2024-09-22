# Javadoc

[Javadoc generates HTML pages of API documentation from Java source files](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html), allowing you to leave comments at the method, class, and package levels. 

<br/><br/>

# Run

```shell
$ ./gradlew build
$ ./gradlew bootRun
```

<br/><br/>

# Contents

You can document class or method-level comments using Javadoc, and generate the documentation with the following commands:

```java
/**
 * This class provides common API functionalities such as health checks.
 */
@RequestMapping(path = "/api")
@RestController(value = "javaCommonApi")
public class JavaCommonApi {

    /**
     * Health check endpoint.
     *
     * @return A ResponseEntity with an HTTP 200 status.
     */
    @GetMapping(path = "/health-check/v1")
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok()
            .build();
    }
}
```

```shell
$ ./gradlew javadoc         # Java
$ ./gradlew dokkaHtml       # Kotlin
```

<br/><br/><br/><br/>

You can also leave package-level comments using package-info.java. [package-info.java](https://www.baeldung.com/java-package-info) is a Java file that allows you to document or comment on the package level. This file allows you to add comments or documentation for the entire package.

```java
@NonNullApi
@NonNullFields
package project.io.app.core.user.application;

import org.springframework.lang.NonNullApi;
import org.springframework.lang.NonNullFields;
```

<br/><br/><br/><br/>

However, caution is required when using this, as it is thoroughly explained in detail in the [Oracle documentation](https://docs.oracle.com/javase/specs/jls/se7/html/jls-7.html).

> The manner in which this restriction is enforced must, of necessity, vary from implementation to implementation. The following scheme is strongly recommended for file-system-based implementations: The sole annotated package declaration, if it exists, is placed in a source file called package-info.java in the directory containing the source files for the package. This file does not contain the source for a class called package-info.java; indeed it would be illegal for it to do so, as package-info is not a legal identifier. Typically package-info.java contains only a package declaration, preceded immediately by the annotations on the package. While the file could technically contain the source code for one or more package-private (default-access) classes, it would be very bad form.

<br/><br/><br/><br/>

To exclude specific packages or classes, you can configure it as follows to omit them from the documentation. When creating Javadoc for a project with module descriptors, the exclude method fails, causing [Javadoc errors](https://github.com/gradle/gradle/issues/19726). You can manually set the source path and exclude specific packages as shown below:

```kotlin
tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions).apply {
        encoding = "UTF-8"
        addStringOption("Xdoclint:none", "-quiet")
        addStringOption("html5", "-quiet")
    }
    
    val directories = sourceSets
        .main
        .get()
        .java
        .sourceDirectories
        .joinToString(":")
    (options as CoreJavadocOptions).addStringOption("-source-path", directories)
    exclude("**/response/**", "**/request/**")
}
```

<br/><br/><br/><br/>

If you are using Kotlin, you can write the code as follows:

```kotlin
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
```

<br/><br/><br/><br/>

Because of a version issue in Dokka, you need to add the following configuration:

```kotlin
configurations.matching { it.name.startsWith("dokka") }.configureEach {
    resolutionStrategy.eachDependency {
        if (requested.group.startsWith("com.fasterxml.jackson")) {
            useVersion("2.15.3")
        }
    }
}
```

<br/><br/><br/><br/>

Otherwise, you will encounter the following error message:

```shell
Execution failed for task ':dokkaHtml'.
> 'void com.fasterxml.jackson.databind.type.TypeFactory.<init>(com.fasterxml.jackson.databind.util.LRUMap)'
```

<br/><br/><br/><br/>

Javadoc is not automatically included in the build process.

```shell
4:56:27PM: Executing 'build'...

> Task :compileKotlin UP-TO-DATE
> Task :compileJava UP-TO-DATE
> Task :processResources UP-TO-DATE
> Task :classes UP-TO-DATE
> Task :resolveMainClassName UP-TO-DATE
> Task :bootJar UP-TO-DATE
> Task :jar UP-TO-DATE
> Task :assemble UP-TO-DATE
> Task :compileTestKotlin NO-SOURCE
> Task :compileTestJava UP-TO-DATE
> Task :processTestResources UP-TO-DATE
> Task :testClasses UP-TO-DATE
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
> Task :test
> Task :check
> Task :build
```

<br/><br/><br/><br/>

If you want to include it in the build process, you need to add the following dependency:

```kotlin
tasks.build {
    dependsOn("javadoc")
}
```
