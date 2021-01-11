plugins {
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.ajoberstar.grgit") version "4.1.0"
    id("org.springframework.boot") version "2.3.4.RELEASE" apply false
    id("io.swagger.swaggerhub") version "1.0.1"
    id("com.diffplug.spotless") version "5.7.0"
    id("com.palantir.docker") version "0.25.0" apply false
    id("com.palantir.docker-run") version "0.25.0" apply false
    id("com.cdsap.talaiot") version "1.4.1" apply false
    id("com.cdsap.talaiot.plugin.base") version "1.4.1" apply false
    id("net.rdrei.android.buildtimetracker") version "0.11.0" apply false
    id("demo.build-time-tracker")
}
