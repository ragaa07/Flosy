# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build the project
./gradlew build

# Clean build
./gradlew clean build

# Assemble debug APK
./gradlew assembleDebug

# Assemble release APK
./gradlew assembleRelease

# Install debug APK on connected device
./gradlew installDebug
```

## Testing Commands

```bash
# Run all unit tests
./gradlew test

# Run unit tests for debug build
./gradlew testDebugUnitTest

# Run a single test class
./gradlew testDebugUnitTest --tests "com.example.flosy.ExampleUnitTest"

# Run instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# Run instrumented tests for debug build
./gradlew connectedDebugAndroidTest
```

## Architecture

This is a single-module Android application using Jetpack Compose for UI.

- **Package**: `com.example.flosy`
- **Min SDK**: 24
- **Target SDK**: 36
- **Build System**: Gradle with Kotlin DSL and version catalog (`gradle/libs.versions.toml`)

### Source Structure

- `app/src/main/java/com/example/flosy/` - Main application code
  - `MainActivity.kt` - Single activity entry point using Compose
  - `ui/theme/` - Material3 theme configuration (Color, Theme, Type)
- `app/src/test/` - Local unit tests (JUnit 4)
- `app/src/androidTest/` - Instrumented tests (AndroidJUnit4)

### Tech Stack

- Kotlin 2.0.21
- Jetpack Compose with Material3
- AndroidX Activity Compose for activity integration
- Compose BOM for version alignment