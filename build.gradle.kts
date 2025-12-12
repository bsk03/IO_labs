plugins {
    id("java")
}

group = "edu.io"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:26.0.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    
    testLogging {
        events("passed", "skipped", "failed", "standardOut", "standardError")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
        showStandardStreams = false
        
        afterSuite(KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
            if (desc.parent == null) {
                println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
                println("Test Results: ${result.resultType}")
                println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
                println("Tests run    : ${result.testCount}")
                println("  ✅ Passed  : ${result.successfulTestCount}")
                println("  ❌ Failed  : ${result.failedTestCount}")
                println("  ⏭️  Skipped : ${result.skippedTestCount}")
                println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n")
            }
        }))
    }
}