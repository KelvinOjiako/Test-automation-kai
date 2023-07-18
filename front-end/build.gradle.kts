import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

//tasks.register<Copy>("copyFrontEndToBackEnd") {
//        dependsOn("jsBrowserProductionWebpack")
//        from("$buildDir/dist")
//        into(":back-end/src/main/resources/client-side")
//}

kotlin {
    js(IR) {
        browser(){
            distribution {
                directory = file("$projectDir/../back-end/src/main/resources/client-side")
            }
        }
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting  {
            dependencies {
                implementation(project(":shared"))
                implementation("com.arkivanov.decompose:decompose-js:2.1.0-compose-experimental-alpha-02")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains-js:2.1.0-compose-experimental-alpha-02")
            }
        }
    }
}

compose.experimental {
    web.application {}
}

rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
    versions.webpackCli.version = "5.0.0"
}