plugins {
    kotlin("multiplatform") version "1.5.0"
}

group = "com.machinerychorus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js(LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    sourceSets {
        val jsMain by getting {}
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}