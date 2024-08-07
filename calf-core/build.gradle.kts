plugins {
    id("compose.multiplatform")
    id("module.publication")
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
    }
}
