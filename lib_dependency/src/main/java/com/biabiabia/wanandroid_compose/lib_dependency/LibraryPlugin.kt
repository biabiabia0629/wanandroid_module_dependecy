package com.biabiabia.wanandroid_compose.lib_dependency

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

open class LibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply(Plugins.com_android_library)
        target.plugins.apply(Plugins.kotlin_android)
        target.configureLibraryAndroid()
        target.configureDependencies()
    }

    private fun Project.configureLibraryAndroid() =
        this.extensions.getByType<LibraryExtension>().run {
            compileSdkVersion(30)
            buildToolsVersion = "30.0.3"

            defaultConfig {
                minSdkVersion(21)
                targetSdkVersion(30)
                versionCode = 1
                versionName = "1.0.0"
                testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

                vectorDrawables.useSupportLibrary = true
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android.txt"),
                        "proguard-rules.pro"
                    )
                }

                getByName("debug") {
                    isTestCoverageEnabled = true
                }
            }

            packagingOptions {
                exclude("META-INF/NOTICE.txt")
                // ...
            }

            buildFeatures.compose = true

            composeOptions {
                kotlinCompilerExtensionVersion = "1.0.0-beta07"
                kotlinCompilerVersion = "1.4.32"
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }
}