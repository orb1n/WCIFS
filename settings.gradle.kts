pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()

        // Modstitch
        maven("https://maven.isxander.dev/releases/")

        // Loom platform
        maven("https://maven.fabricmc.net/")

        // MDG platform
        maven("https://maven.neoforged.net/releases/")

        // Stonecutter
        maven("https://maven.kikugie.dev/releases")
        maven("https://maven.kikugie.dev/snapshots")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.9+"
}

stonecutter {
    kotlinController = true
    centralScript = "build.gradle.kts"

    create(rootProject) {
        fun mc(mcVersion: String, name: String = mcVersion, loaders: Iterable<String>) =
            loaders.forEach { version("$name-$it", mcVersion) }

        mc("1.17", loaders = listOf("fabric"))
        mc("1.19", loaders = listOf("fabric"))
        mc("1.19.2", loaders = listOf("fabric"))
        mc("1.21", loaders = listOf("neoforge"))
        mc("26.1", loaders = listOf("fabric", "neoforge"))
        mc("26.2", loaders = listOf("fabric", "neoforge"))

        vcsVersion = "26.2-fabric"
    }
}

rootProject.name = "WCIFS"