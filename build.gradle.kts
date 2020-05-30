import net.minecrell.pluginyml.bukkit.BukkitPluginDescription.Permission.Default

plugins {
    kotlin("jvm") version "1.3.72"
    id("net.minecrell.plugin-yml.bukkit") version "0.3.0"
}

group = "me.syari.fjg"
version = "1.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
    }
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencies {
    compileOnly(kotlin("stdlib-jdk8"))
    implementation("org.spigotmc:spigot-api:1.8-R0.1-SNAPSHOT")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

bukkit {
    name = project.name
    version = project.version.toString()
    main = "$group.Main"
    author = "sya_ri"
    commands {
        register("firstjoingift"){
            description = "FirstJoinGiftに関するコマンドです"
            aliases = listOf("fjg")
            permission = "fjg.command.run"
            permissionMessage = "あなたはこのコマンドを実行するのに必要な権限を持っていません"
        }
    }
    permissions {
        register("fjg.command.run") {
            default = Default.OP
            description = "/fjg を実行するのに必要な権限です"
        }
    }
}

val jar by tasks.getting(Jar::class) {
    from(configurations.compileOnly.get().map {
        if (it.isDirectory) it else zipTree(it)
    })
}