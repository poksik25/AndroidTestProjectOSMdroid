pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url =
                uri("https://jitpack.io")//Unexpected tokens (use ';' to separate expressions on the same line)
        }

    }
}

rootProject.name = "AndroidTestProjectOSMdroid"
include(":app")
