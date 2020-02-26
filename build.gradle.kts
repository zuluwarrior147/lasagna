plugins {
    scala
    application
    `java-library`
    id("com.github.maiflai.scalatest") version "0.26"
}

repositories {
    jcenter()
}

val scalaVersion = "2.12.9"
val scalaCompatVersion = "2.12"

dependencies {
    implementation("org.scala-lang:scala-library:$scalaVersion")
    implementation("org.mlflow:mlflow-client:1.6.0")
    implementation("com.github.scopt:scopt_$scalaCompatVersion:3.7.1")

    testImplementation("org.scalatest:scalatest_$scalaCompatVersion:3.0.8")
    testRuntimeOnly("org.pegdown:pegdown:1.4.2")
}

application {
    mainClassName = "lasagna.Main"
}
