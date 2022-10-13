# Kotlinize your CI

This project is a sample of the various ways
I introduced Kotlin in our Android CI.

This was used in:
+ a DroidKaigi prensentation https://bit.ly/kotlin-ci
+ a [Mercari blog-post](https://engineering.mercari.com/en/blog/entry/20221012-leverage-kotlin-in-your-android-ci/)

## Features

The project is including 2 separate projects:
+ the [./android](./android) project representing a consumer Android codebase.
+ the [./toolbox](./toolbox) project implementing Docker shipped utilities used in the consumer projects.

This is showcasing following strategies:
+ Custom docker images
+ JavaExec tasks in a composite build (see [./android/scripts](./android/scripts))
+ Kotlin scripts
