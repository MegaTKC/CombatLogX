plugins {
    id("distribution")
}

val coreVersion = rootProject.ext.get("coreVersion") as String
val coreJar: Configuration by configurations.creating
val pluginJar: Configuration by configurations.creating
val expansion: Configuration by configurations.creating

dependencies {
    // BlueSlimeCore
    coreJar("com.github.sirblobman.api:core:$coreVersion")

    // CombatLogX
    pluginJar(project(path = ":plugin", configuration = "shadow"))

    // Normal Expansions
    expansion(project(":expansion:action-bar"))
    expansion(project(":expansion:boss-bar"))
    expansion(project(path = ":expansion:cheat-prevention", configuration = "shadow"))
    expansion(project(":expansion:damage-tagger"))
    expansion(project(":expansion:damage-effects"))
    expansion(project(":expansion:death-effects"))
    expansion(project(path = ":expansion:end-crystal", configuration = "shadow"))
    expansion(project(":expansion:force-field"))
    expansion(project(":expansion:glowing"))
    expansion(project(":expansion:logger"))
    expansion(project(path = ":expansion:loot-protection", configuration = "shadow"))
    expansion(project(":expansion:mob-tagger"))
    expansion(project(":expansion:newbie-helper"))
    expansion(project(":expansion:rewards"))
    expansion(project(":expansion:scoreboard"))

    // Compatibility expansions
    expansion(project(":expansion:compatibility:AngelChest"))
    expansion(project(":expansion:compatibility:ASkyBlock"))
    expansion(project(":expansion:compatibility:BSkyBlock"))
    expansion(project(":expansion:compatibility:Citizens"))
    expansion(project(":expansion:compatibility:CMI"))
    expansion(project(":expansion:compatibility:CrashClaim"))
    expansion(project(":expansion:compatibility:EssentialsX"))
    expansion(project(":expansion:compatibility:Factions"))
    expansion(project(":expansion:compatibility:GriefDefender"))
    expansion(project(":expansion:compatibility:GriefPrevention"))
    expansion(project(":expansion:compatibility:HuskHomes"))
    expansion(project(":expansion:compatibility:HuskSync"))
    expansion(project(":expansion:compatibility:HuskTowns"))
    expansion(project(":expansion:compatibility:iDisguise"))
    expansion(project(":expansion:compatibility:IridiumSkyblock"))
    expansion(project(":expansion:compatibility:KingdomsX"))
    expansion(project(":expansion:compatibility:Konquest"))
    expansion(project(":expansion:compatibility:Lands"))
    expansion(project(":expansion:compatibility:LibsDisguises"))
    expansion(project(":expansion:compatibility:LuckPerms"))
    expansion(project(":expansion:compatibility:MarriageMaster"))
    expansion(project(":expansion:compatibility:MCPets"))
    expansion(project(":expansion:compatibility:MythicMobs"))
    expansion(project(":expansion:compatibility:PlaceholderAPI"))
    expansion(project(":expansion:compatibility:PlayerParticles"))
    expansion(project(":expansion:compatibility:RedProtect"))
    expansion(project(":expansion:compatibility:Residence"))
    expansion(project(":expansion:compatibility:SuperiorSkyblock"))
    expansion(project(":expansion:compatibility:SuperVanish"))
    expansion(project(":expansion:compatibility:Towny"))
    expansion(project(":expansion:compatibility:UltimateClaims"))
    expansion(project(":expansion:compatibility:uSkyBlock"))
    expansion(project(":expansion:compatibility:VanishNoPacket"))
    expansion(project(":expansion:compatibility:ZNPCsPlus"))
}

distributions {
    main {
        contents {
            into("/") {
                // README.TXT
                from(sourceSets.getByName("main").allSource)

                // CombatLogX.jar
                from(pluginJar)

                // BlueSlimeCore.jar
                from(coreJar)
                rename("core-$coreVersion.jar", "BlueSlimeCore.jar")
            }

            into("/CombatLogX/expansions/") {
                // All Expansions
                from(expansion)
            }
        }
    }
}

tasks {
    named<Jar>("jar") {
        enabled = false
    }

    named<Tar>("distTar") {
        enabled = false
    }

    named<Zip>("distZip") {
        isPreserveFileTimestamps = true
        archiveBaseName.set("CombatLogX")
        version = rootProject.ext.get("calculatedVersion") as String
    }
}
