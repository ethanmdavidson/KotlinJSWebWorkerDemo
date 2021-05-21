plugins {
    //I use the multiplatform plugin because it is more flexible,
    // but I think you could do the same thing with the JS plugin.
    kotlin("multiplatform") version "1.5.0"
    kotlin("plugin.serialization") version "1.5.0"
}

group = "com.machinerychorus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
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
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.3")
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

/*This task just copies files from build/distributions to /docs
    to make it easier to update gh-pages  */
task("copyDistToDocs", Copy::class) {
    from("$buildDir/distributions/")
    into("/docs")
}
tasks.named("copyDistToDocs"){ dependsOn("build") }