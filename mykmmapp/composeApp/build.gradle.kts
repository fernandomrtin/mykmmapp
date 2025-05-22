import org.jetbrains.kotlin.gradle.dsl.JvmTarget // Necesitarás este import

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
            // "this" aquí es cada KotlinCompilation (por ejemplo, debug, release)
            this.compileTaskProvider.configure {
                // Forma moderna y más directa con el DSL anidado:
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
    }

    // 2. Targets iOS (requeridos para XCFramework)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // 4. Configuración de sourceSets
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)

                implementation(libs.material3)

                implementation(project(":shared")) // Descomenta si existe
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.appcompat)
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