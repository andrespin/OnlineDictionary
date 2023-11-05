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
    }
}

rootProject.name = "OnlineDictionary"
include(":app")
include(":data")
include(":presentation")
include(":domain")
include(":data:remote")
include(":data:local")
include(":presentation:settings")
include(":presentation:dictionary")
include(":presentation:about_app")
