pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Flosy"
include(":app")

// Core modules
include(":core:common")
include(":core:database")
include(":core:data")
include(":core:domain")

// Design System
include(":designSystem")

// Feature modules
include(":features:home")
include(":features:onboarding")
include(":features:expenses")
include(":features:profile")
include(":features:settings")
