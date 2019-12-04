import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
    kotlin("kapt") version "1.3.31"
    id("com.github.johnrengelman.shadow") version "5.0.0"
    id("flavor.pie.promptsign") version "1.1.0"
    id("maven-publish")
    id("net.minecrell.licenser") version "0.4.1"
}

group = "flavor.pie"
version = "0.1.0"

repositories {
    mavenCentral()
    maven {
        name = "sponge"
        url = uri("https://repo.spongepowered.org/maven/")
    }
    maven {
        name = "jitpack"
        url = uri("https://jitpack.io/")
    }
    maven {
        name = "bstats"
        url = uri("https://repo.codemc.org/repository/maven-public")
    }
}

dependencies {
    val sponge = create(group = "org.spongepowered", name = "spongeapi", version = "7.1.0")
    api(sponge)
    kapt(sponge)
    val kotlin = kotlin("stdlib-jdk8")
    api(kotlin)
    shadow(kotlin)
    val kludge = create(group = "com.github.pie-flavor", name = "kludge", version = "477392a")
    implementation(kludge)
    shadow(kludge)
    val bstats = create(group = "org.bstats", name = "bstats-sponge-lite", version = "1.4")
    implementation(bstats)
    shadow(bstats)
}

tasks.named("jar") {
    enabled = false
}

val shadowJar = tasks.named<ShadowJar>("shadowJar") {
    configurations = listOf(project.configurations.shadow.get())
    archiveClassifier.set("")
    relocate("kotlin", "flavor.pie.shulkershell.runtime.kotlin")
    relocate("flavor.pie.kludge", "flavor.pie.shulkershell.util.kludge")
    minimize()
}

tasks.build {
    dependsOn(shadowJar)
}

tasks.named("signArchives") {
    dependsOn(shadowJar)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    publications {
        create("sponge", MavenPublication::class.java) {
            project.shadow.component(this)
            pom {
                name.set("ShulkerShell")
                description.set("An alternative, shell-based command system.")
                url.set("https://ore.spongepowered.org/pie_flavor/shulkershell/")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/pie-flavor/ShulkerShell/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("pie_flavor")
                        name.set("Adam Spofford")
                        email.set("aspofford.as@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/pie-flavor/ShulkerShell.git")
                    developerConnection.set("scm:git:ssh://github.com/pie-flavor/ShulkerShell.git")
                    url.set("https://github.com/pie-flavor/ShulkerShell/")
                }
            }
        }
        repositories {
            val githubToken: String? by project
            if (githubToken != null) {
                maven {
                    url = uri("https://maven.pkg.github.com/pie-flavor/ShulkerShell")
                    credentials {
                        username = "pie-flavor"
                        password = githubToken
                    }
                }
            }
        }
    }
}

license {
    header = project.file("HEADER.txt")
    exclude("**/*.conf")
}
