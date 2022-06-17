import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    application
}

group = "com.programistich"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

// gradle :find-file -Pfile="Main.kt"
// gradle :find-file -Pfile="/Users/programistich/Projects/Study/summer-2/Automatization Java/Practice-2/build.gradle.kts"
tasks.register<Task>("find-file") {
    val nameFile = project.properties["file"] as String? ?: return@register
    val file = File(nameFile)
    val files = fileTree("${project.projectDir}")
        .files
        .map { it.name }
        .filter { it == file.name }
    if (files.isEmpty()) println("Not Found files")
    else println("Exist files: ${files.size}")
}

// gradle :copy-to-jar -Pfile="Main.kt"
tasks.register<Jar>("copy-to-jar") {
    val nameFile = project.properties["file"] as String? ?: return@register
    val jar = archiveFile.get().asFile.name
    from(nameFile)
    into(file("${project.buildDir}/libs/$jar"))
}
