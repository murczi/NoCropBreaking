plugins {
    `java-library`
}

group = "me.shuji.nobreakcrops"
version = "1.0.2"
description = "Simple plugin for keeping crops safe from breaking"

val targetJava = (findProperty("targetJava") as String?)?.toInt() ?: 21
val paperApiVersion = (findProperty("paperApiVersion") as String?) ?: "1.21.11-R0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:$paperApiVersion")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(targetJava))
}

tasks {
    compileJava {
        options.release.set(targetJava)
    }

    jar {
        archiveClassifier.set("java$targetJava")
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
}
