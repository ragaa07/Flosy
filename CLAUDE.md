# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build Android app
./gradlew :app:assembleDebug

# Clean build
./gradlew clean :app:assembleDebug

# Install Android app on connected device
./gradlew :app:installDebug

# Build iOS framework
./gradlew :app:iosSimulatorArm64Binaries

# Build release APK
./gradlew :app:assembleRelease
```

## Testing Commands

```bash
# Run all tests
./gradlew test

# Run common tests
./gradlew :app:testDebugUnitTest
```

## Architecture

This is a **Compose Multiplatform (CMP)** project sharing UI and business logic across Android and iOS.

- **Package**: `com.flosy.app`
- **Min SDK**: 24 (Android)
- **Target SDK**: 35 (Android)
- **Build System**: Gradle with Kotlin DSL and version catalog (`gradle/libs.versions.toml`)

### Project Structure

```
Flosy/
├── app/                    # Shared Compose UI + business logic
│   └── src/
│       ├── commonMain/            # Shared code (UI + logic)
│       │   └── kotlin/com/flosy/app/
│       │       ├── App.kt         # Main composable entry point
│       │       ├── Platform.kt    # Platform expect declaration
│       │       └── ui/theme/      # Material3 theme (Color, Theme, Type)
│       ├── androidMain/           # Android-specific code
│       │   └── kotlin/com/flosy/app/
│       │       ├── MainActivity.kt
│       │       └── Platform.android.kt
│       └── iosMain/               # iOS-specific code
│           └── kotlin/com/flosy/app/
│               ├── MainViewController.kt
│               └── Platform.ios.kt
└── iosApp/                        # iOS app wrapper (Swift)
    └── iosApp/
        ├── iOSApp.swift           # iOS entry point
        └── ContentView.swift      # Hosts ComposeView
```

### Tech Stack

- Kotlin 2.0.21
- Compose Multiplatform 1.7.1
- Material3 Design
- Kotlin Coroutines
- Kotlin Serialization
- Navigation Compose (Multiplatform)

### Adding New Screens

1. Create composables in `app/src/commonMain/kotlin/com/flosy/app/`
2. Platform-specific code goes in `androidMain` or `iosMain` with `expect`/`actual`
3. The `App()` composable in `App.kt` is the root of the UI tree
