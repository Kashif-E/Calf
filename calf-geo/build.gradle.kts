plugins {
    id("compose.multiplatform")
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
    }
    sourceSets.androidMain.dependencies {
        implementation(libs.appcompat)
        implementation(libs.lifecycle.extensions)
        implementation(libs.play.services.location)
    }
}
