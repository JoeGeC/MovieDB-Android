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

rootProject.name = "MovieDB"
include(":app")
include(":core:domain")
include(":core:data")
include(":core:presentation")
include(":feature:movieDetails")
include(":feature:popularMovies")
include(":feature:popularTvShows")
include(":feature:popular")
