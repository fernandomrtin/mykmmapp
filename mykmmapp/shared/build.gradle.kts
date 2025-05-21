// shared/build.gradle.kts
plugins {
    kotlin("multiplatform")
    id("com.android.library") // Solo si necesitas Android
}

android {
    namespace = "org.fernandommdev.mykmmapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = 21  // Mínimo SDK soportado
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
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