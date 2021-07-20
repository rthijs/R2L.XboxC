# Easily add controller support to your project

## Run

Simply execute `java -jar desktop-1.0.jar` or chmod +x the jar and run it by double clicking.

## Build
- make sure to have sourceCompatibility at 1.8 or higher, I use 11. This can be set in the IDE for the project or in the files `core/build.gradle` and `desktop/build.gradle`.
- from the project directory execute `./gradlew desktop:dist`, this creates an executable jar.