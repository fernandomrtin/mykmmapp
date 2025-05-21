// shared/build.gradle.kts
plugins {
    kotlin("multiplatform")
    id("com.android.library") // Solo si necesitas Android
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            // Tus dependencias comunes aquí
        }
    }
}