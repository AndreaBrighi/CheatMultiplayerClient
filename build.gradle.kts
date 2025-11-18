import io.github.andreabrighi.gradle.gitsemver.conventionalcommit.ConventionalCommit

plugins {
    alias(libs.plugins.gitSemVer)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.qa)
    alias(libs.plugins.taskTree)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)
    alias(libs.plugins.multiJvmTesting)
}

gitSemVer {
    maxVersionLength.set(20)
    buildMetadataSeparator.set("-")
    commitNameBasedUpdateStrategy(ConventionalCommit::semanticVersionUpdate)
}

buildscript {
    dependencies {
        classpath(libs.convetional)
    }
}

allprojects {
    group = "it.unibo.ds"
    version = "0.1.0"

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlinx.kover")

    tasks.test {
        useJUnitPlatform()
    }

    multiJvm {
        jvmVersionForCompilation = oldestJavaSupportedByGradle
        maximumSupportedJvmVersion = latestJavaSupportedByGradle
    }
}
