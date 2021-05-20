plugins {
    //I use the multiplatform plugin because it is more flexible,
    // but I think you could do the same thing with the JS plugin.
    kotlin("multiplatform") version "1.5.0"
}

group = "com.machinerychorus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                outputFileName = "client.js"
                cssSupport.enabled = true
            }
        }
    }
    js("webworker", IR) {
        binaries.executable()
        browser{
            commonWebpackConfig {
                outputFileName = "worker.js"
            }
        }
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.2")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val webworkerMain by getting {
            dependencies {
                //worker has separate deps from main
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.0")
            }
        }
    }
}

//tasks.named("jsBrowserRun"){ dependsOn("webworkerBrowserWebpack") }