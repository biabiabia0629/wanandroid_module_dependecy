plugins {
    id("java-library")
    id("kotlin")
    `kotlin-dsl`
}

apply(from = "publish_gradle.gradle")

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}


allprojects {
    repositories {
        mavenCentral()
        google()
        maven {
            isAllowInsecureProtocol = true
            setUrl("http://127.0.0.1:8081/repository/maven-public/")
            credentials {
                username = "default_user"
                password = "default_user"
            }
        }
    }
}



dependencies {
    /* Example Dependency */
    /* Depend on the android gradle plugin, since we want to access it in our plugin */
    implementation("com.android.tools.build:gradle:4.2.0")

    /* Example Dependency */
    /* Depend on the kotlin plugin, since we want to access it in our plugin */
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")

    /* Depend on the default Gradle API's since we want to build a custom plugin */
    implementation(gradleApi())
    implementation(localGroovy())
}