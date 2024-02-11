import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("fabric-loom") version "1.4-SNAPSHOT"
    id("maven-publish")
    kotlin("jvm") version "1.9.22"
}

group = project.properties["maven_group"]!!
version = project.properties["mod_version"]!!
base.archivesName.set(project.properties["archives_base_name"] as String)

val githubRepo = project.properties["github_repo"]!! as String

repositories {
    maven("https://maven.quiltmc.org/repository/release/")
    maven("https://api.modrinth.com/maven") {
        name = "Modrinth"
        content { includeGroup("maven.modrinth") }
    }
}

loom {
    splitEnvironmentSourceSets()

    accessWidenerPath = file("src/main/resources/perception.accesswidener")

    mods {
        create("perception") {
            sourceSet(sourceSets["main"])
            sourceSet(sourceSets["client"])
        }
    }
}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${project.properties["minecraft_version"]}")
    mappings("org.quiltmc:quilt-mappings:${project.properties["minecraft_version"]}+build.${project.properties["quilt_mappings"]}:intermediary-v2")
    modImplementation("net.fabricmc:fabric-loader:${project.properties["loader_version"]}")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.properties["fabric_version"]}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${project.properties["fabric_kotlin_version"]}")
    modImplementation(include("maven.modrinth:midnightlib:${project.properties["midnightlib_version"]}")!!)
    // Uncomment the following line to enable the deprecated Fabric API modules.
    // These are included in the Fabric API production distribution and allow you to update your mod to the latest modules at a later more convenient time.

    // modImplementation "net.fabricmc.fabric-api:fabric-api-deprecated:${project.fabric_version}"
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }

    withType<JavaCompile>().configureEach {
        options.release = 17
    }

    withType<KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    jar {
        from("LICENSE") {
            rename { "${it}_${project.base.archivesName.get()}"}
        }
    }
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// configure the maven publication
publishing {
    publications {
        register<MavenPublication>("perception") {
            from(components["java"])
            this.groupId = project.group.toString()
            this.artifactId = base.archivesName.get()
            this.version = project.version.toString()

            pom {
                name.set(project.name)
                description.set(project.description)

                developers {
                    developer {
                        name = "WiredTomato"
                    }
                }

                licenses {
                    license {
                        name = "MIT"
                        url.set("https://github.com/$githubRepo/blob/master/LICENSE")
                    }
                }

                url.set("https://github.com/$githubRepo")

                scm {
                    connection.set("scm:git:git://github.com/$githubRepo.git")
                    url.set("https://github.com/$githubRepo/tree/main")
                }
            }
        }
    }

    repositories {
        maven {
            name = "WiredTomato"
            url = uri("https://maven.wiredtomato.net/public")
            credentials(PasswordCredentials::class)
        }
    }
}
