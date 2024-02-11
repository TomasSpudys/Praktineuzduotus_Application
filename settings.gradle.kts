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
        jcenter() // Warning: this repository is going to shut down soon

        // e.g this is how you would add jitpack
        maven( "https://jitpack.io" )
        // Add any repositories you would be adding to all projects here
    }
}

rootProject.name = "Demo_Application_Tomas"
include(":app")
 