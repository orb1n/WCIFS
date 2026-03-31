plugins {
    id("dev.kikugie.stonecutter")
}

stonecutter.active("26.1-fabric")

stonecutter.tasks {
    order("publishModrinth")
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
        maven("https://maven.neoforged.net/releases")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.parchmentmc.org/")
    }
}