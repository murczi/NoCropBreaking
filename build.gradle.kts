plugins {
	`java-library`
	id("io.papermc.paperweight.userdev") version "1.7.1"
	id("xyz.jpenilla.run-paper") version "2.3.0"
}

group = "me.shuji.nobreakcrops"
version = "1.0.2"
description = "Simple plugin for keeping crops safe from breaking"


java {
	// Configure the java toolchain. This allows gradle to auto-provision JDK 21 on systems that only have JDK 11 installed for example.
	toolchain.languageVersion = JavaLanguageVersion.of(21)
}

// 1)
// For >=1.20.5 when you don't care about supporting spigot
// paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

// 2)
// For 1.20.4 or below, or when you care about supporting Spigot on >=1.20.5
// Configure reobfJar to run when invoking the build task
/*
tasks.assemble {
  dependsOn(tasks.reobfJar)
}
 */

dependencies {
	paperweight.paperDevBundle("1.21-R0.1-SNAPSHOT")
}

tasks {
	compileJava {
		options.release = 21
	}
	javadoc {
		options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
	}

	// Only relevant when going with option 2 above
	/*
	reobfJar {
			// This is an example of how you might change the output location for reobfJar. It's recommended not to do this
			// for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
			outputJar = layout.buildDirectory.file("libs/PaperweightTestPlugin-${project.version}.jar")
	}
		*/
}

// Configure plugin.yml generation
// - name, version, and description are inherited from the Gradle project.
/*bukkitPluginYaml {
	main = "me.shuji.joinmessage.Main"
	load = BukkitPluginYaml.PluginLoadOrder.STARTUP
	author = "Shuji"
	apiVersion = "1.21"

}*/
