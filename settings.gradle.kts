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
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MovieDB"
include(":app")
include(":core:domain")
include(":core:data")
include(":core:presentation")
include(":feature:details:movies")
include(":feature:popular:movies")
include(":feature:popular:tv")
include(":feature:popular:base")
include(":feature:details:base")
include(":feature:details:tv")
include(":feature:cast:base")
include(":feature:cast:movies")
include(":feature:cast:tv")
