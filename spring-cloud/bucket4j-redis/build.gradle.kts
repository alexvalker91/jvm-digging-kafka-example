plugins {
    id("com.google.cloud.tools.jib")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.redisson:redisson")

    implementation("com.bucket4j:bucket4j_jdk17-redis-common")
    implementation("com.bucket4j:bucket4j_jdk17-redisson")
}

jib {
    container {
        creationTime.set("USE_CURRENT_TIMESTAMP")
    }
    from {
        image = "bellsoft/liberica-openjdk-alpine-musl:21.0.1"
    }

    to {
        image = "localrun/bucket4j-redis"
        tags = setOf(project.version.toString())
    }
}

tasks {
    build {
        dependsOn(spotlessApply)
        dependsOn(jibBuildTar)
    }
}