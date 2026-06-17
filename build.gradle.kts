plugins {
    id("dev.isxander.modstitch.base") version "0.8.4"
    id("dev.isxander.modstitch.publishing") version "0.8.4"
}

fun prop(name: String, consumer: (prop: String) -> Unit) {
    (findProperty(name) as? String?)
        ?.let(consumer)
}

modstitch {
    prop("deps.minecraft") { minecraftVersion = it }

    parchment {
        prop("parchment.version") { mappingsVersion = it }
        prop("parchment.minecraft") { minecraftVersion = it }
    }

    metadata {
        modId = "wcifs"
        modName = "WCIFS"
        modVersion = "1.3.1+${stonecutter.current.project}"
        modDescription = "This mod tells you how long it is until night when clicking on a bed."
        modLicense = "CC BY-NC-SA 4.0"
        modGroup = "dev.orb1n"
        modAuthor = "orb1n"

        replacementProperties.put("mod_modrinth", "https://modrinth.com/mod/wcifs")
        replacementProperties.put("mod_sources", "https://github.com/orb1n/WCIFS")
        replacementProperties.put("mod_issues", "https://github.com/orb1n/WCIFS/issues")
        replacementProperties.put("minecraft", property("deps.minecraft.constraint") as String)
        replacementProperties.put("fabric_api", if (stonecutter.eval(stonecutter.current.version, ">=1.19.2")) "fabric-api" else "fabric")
    }

    loom {
        fabricLoaderVersion = "0.18.5"
    }

    moddevgradle {
        prop("deps.neoforge") { neoForgeVersion = it }
        prop("deps.forge") { forgeVersion = it }

        defaultRuns()

        configureNeoForge {
            runs.all {
                disableIdeRun()
            }
        }
    }

    mixin {
        addMixinsToModManifest = true

        configs.register("wcifs")
    }
}

msPublishing {
    mpp {
        file = modstitch.finalJarTask.get().archiveFile
        displayName = "${modstitch.metadata.modName.get()} ${modstitch.metadata.modVersion.get()}"
        type = STABLE
        dryRun = providers.gradleProperty("modrinth_token").orNull == null

        modrinth {
            accessToken = providers.gradleProperty("modrinth_token").orNull

            projectId = "LymwTWwC"
            changelog = rootProject.file("CHANGELOG.md").readText()
            projectDescription = rootProject.file("README.md").readText()

            minecraftVersionRange {
                prop("publish.minecraft_version.min") { start = it }
                prop("publish.minecraft_version.max") { end = it }
            }

            requires("fabric-api")
        }
    }
}

stonecutter {
    var loader: String = name.split("-")[1]

    constants.match(
        loader,
        "fabric",
        "neoforge"
    )
}

dependencies {
    modstitch.loom {
        modstitchModImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
    }
}

tasks.register<Copy>("buildAndCollect") {
    group = "build"
    from(modstitch.finalJarTask.map { it.archiveFile } )
    into(rootProject.layout.buildDirectory.dir("collected/${modstitch.metadata.modVersion.get().split("+")[0]}"))
    dependsOn("build")
}