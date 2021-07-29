package com.biabiabia.wanandroid_compose.lib_dependency

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.AndroidBasePlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.util.*

fun Project.configureDependencies() = dependencies {
    if (project.containsAndroidPlugin()) {
        add(Dependencies.implementation, Dependencies.androidx_core_core_ktx)
        add(Dependencies.implementation, Dependencies.androidx_appcompat_appcompat)
        add(Dependencies.implementation, Dependencies.com_google_android_material_material)
        add(Dependencies.implementation, Dependencies.androidx_compose_ui_ui)
        add(Dependencies.implementation, Dependencies.androidx_compose_material_material)
        add(Dependencies.implementation, Dependencies.androidx_compose_ui_ui_tooling)
        add(Dependencies.implementation, Dependencies.androidx_lifecycle_lifecycle_runtime_ktx)
        add(Dependencies.implementation, Dependencies.androidx_activity_activity_compose)
        add(Dependencies.testImplementation, Dependencies.junit_junit)
        add(Dependencies.androidTestImplementation, Dependencies.androidx_test_ext_junit)
        add(
            Dependencies.androidTestImplementation,
            Dependencies.androidx_test_espresso_espresso_core
        )
        add(Dependencies.androidTestImplementation, Dependencies.androidx_compose_ui_ui_test_junit4)
    }
}

fun Project.containsAndroidPlugin(): Boolean {
    return project.plugins.toList().any { plugin -> plugin is AndroidBasePlugin }
}

fun Project.configureDefaultModulePlugins() {
    plugins.apply(Plugins.com_android_library)
    plugins.apply(Plugins.kotlin_android)
    plugins.apply(Plugins.maven_publish)
    configureLibraryAndroid()
    configureDependencies()
}

fun Project.configureDefaultAppPlugins() {
    plugins.apply(Plugins.com_android_application)
    plugins.apply(Plugins.kotlin_android)
    configureAppAndroid()
    configureDependencies()
}

fun Project.configureAppAndroid(versionCode: Int = 1, versionName: String = "1.0.0") =
    this.extensions.getByType<AppExtension>().run {
        compileSdkVersion(30)
        buildToolsVersion = "30.0.3"

        defaultConfig {
            minSdkVersion(21)
            targetSdkVersion(30)
            this.versionCode = versionCode
            this.versionName = versionName

            testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

            vectorDrawables.useSupportLibrary = true
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
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

fun Project.configureLibraryAndroid() =
    this.extensions.getByType<LibraryExtension>().run {
        compileSdkVersion(30)
        buildToolsVersion = "30.0.3"

        defaultConfig {
            minSdkVersion(21)
            targetSdkVersion(30)
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