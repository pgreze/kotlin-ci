# Scripts Composite Build

This project is including all dev-ops related tasks that:

- are not requiring any dependencies from the main mercari-android project,
- providing tooling from command line, etc.

## Local changes

In order to work on this project, add in your ~/.gradle/gradle.properties
(defined in ../settings.gradle.kts):

```
importScripts=true
```

Being a composite build, it may be difficult to run tests for example.
You have 2 solutions:

- fallback to command line with `./gradlew -p script test` (easy)
- open IDEA for this specific project if installed (requires installation via Jetbrains Toolbox)
