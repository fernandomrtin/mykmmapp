plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    // 1. Configuración de targets
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm("desktop") // Target para Desktop (opcional)

    // 2. Targets iOS (requeridos para XCFramework)
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true // Importante para iOS

            // 3. Exporta dependencias necesarias
            export(compose.runtime)
            export(compose.foundation)
            export(compose.material)
            export(project(":shared")) // Descomenta si tienes módulo shared
        }
    }

    // 4. Configuración de sourceSets
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)

                // implementation(project(":shared")) // Descomenta si existe
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.appcompat)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

// 5. Configuración Android obligatoria
android {
    namespace = "org.fernandommdev.mykmmapp" // Cambia a tu namespace
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}