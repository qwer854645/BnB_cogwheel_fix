plugins {
    java
    id("net.neoforged.moddev") version "1.0.21"
}

version = project.property("mod_version") as String
group = project.property("mod_group") as String

base {
    archivesName.set("bnb_cogwheel_compat")
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

neoForge {
    version = project.property("neo_version") as String

    parchment {
        minecraftVersion = project.property("minecraft_version") as String
        mappingsVersion = "2024.11.17"
    }

    runs {
        register("client") {
            client()
        }
    }

    mods {
        register("bnb_cogwheel_compat") {
            sourceSet(sourceSets.main.get())
        }
    }
}

repositories {
    mavenCentral()
    maven("https://maven.neoforged.net/releases")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.named<ProcessResources>("processResources") {
    val props = mapOf("mod_version" to project.version)
    inputs.properties(props)
    filesMatching("META-INF/neoforge.mods.toml") {
        expand(props)
    }
}
