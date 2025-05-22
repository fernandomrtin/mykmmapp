import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("native.cocoapods") // ¡Este plugin es esencial!
}

kotlin {
    androidTarget()
    val xcf = XCFramework()
    val iosTargets = listOf(iosX64(), iosArm64(), iosSimulatorArm64())

    iosTargets.forEach {
        it.binaries.framework {
            baseName = "shared"
            xcf.add(this)
        }
    }

    cocoapods {
        summary = "Shared module"
        homepage = "https://github.com/fernandomrtin/mykmmapp"
        version = "1.0"
        ios.deploymentTarget = "15.0"
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Tus dependencias compartidas
        }
    }
}

android {
    namespace = "org.fernandommdev.mykmmapp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = 24
    }
}