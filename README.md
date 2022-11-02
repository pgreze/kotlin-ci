# Kotlinize your CI

This project contains samples of the various ways I introduced Kotlin in our Mercari app's Android CI.

I shared this project in:
+ a DroidKaigi prensentation: https://bit.ly/kotlin-ci
+ a Mercari blog post: https://engineering.mercari.com/en/blog/entry/20221012-leverage-kotlin-in-your-android-ci/

## Features

The two samples included in this project are:
+ a [./android](./android) project representing a consumer Android codebase.
+ a [./toolbox](./toolbox) project implementing Docker-shipped utilities used in consumer projects.

In this project, I showcase the following strategies:
+ Custom docker images
+ JavaExec tasks in a composite build (see [./android/scripts](./android/scripts))
+ Kotlin scripts
