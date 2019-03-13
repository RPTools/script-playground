

plugins {
    application
    antlr
    eclipse
    jacoco
    id("com.diffplug.gradle.spotless") version "3.18.0"
    id("org.openjfx.javafxplugin") version "0.0.5"
}

group = "net.rptools.script.playground"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    antlr("org.antlr:antlr4:4.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
    compile("org.reflections", "reflections", "0.9.11")
    compile("org.apache.commons", "commons-text", "1.6")
    compile("com.jfoenix", "jfoenix", "9.0.1")
    compile("com.github.jknack:handlebars:4.1.2")
    implementation("net.rptools.dice:dice:latest.integration") {
        version {
            branch = "development"
        }
    }
}

javafx {
    version = "11.0.2"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

application {
    mainClassName = "net.rptools.script.playground.Playground"
}


spotless {

    java {
        target("src/**/*.java")
        licenseHeaderFile(file("build-resources/spotless.license.java"))
        googleJavaFormat()
        // https://github.com/diffplug/spotless/blob/master/PADDEDCELL.md
        paddedCell()
    }

    format("misc") {
        target("**/*.gradle", "**/.gitignore")

        // spotless has built-in rules for most basic formatting tasks
        trimTrailingWhitespace()
        // or spaces. Takes an integer argument if you don't like 4
        indentWithSpaces(4)
        // https://github.com/diffplug/spotless/blob/master/PADDEDCELL.md
        paddedCell()
    }
}

tasks.jar {
    manifest {
        attributes(
                "Implementation-Title" to "Dice Playground",
                "Implementation-Version" to version,
                "Main-Class" to "net.rptools.script.playground.Playground"
        )
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

task<Jar>("uberJar") {
    appendix = "uber"

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from(Callable {
        configurations.runtimeClasspath.filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
    exclude("META-INF/*.RSA', 'META-INF/*.SF','META-INF/*.DSA")
    manifest {
        attributes(
                "Implementation-Title" to "Dice",
                "Implementation-Version" to version,
                "Main-Class" to "net.rptools.script.playground.Test"
        )
    }

}

jacoco {
    toolVersion = "0.8.3"
    reportsDir = file("build/reports/jacoco")
}


