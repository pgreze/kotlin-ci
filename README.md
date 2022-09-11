# Kotlinize your CI

This project is a sample of the various ways
I introduced Kotlin in our Android CI at Mercari.

This was used in:
+ a DroidKaigi prensentation (TODO: link)
+ a Mercari blog-post (TODO: link)

The project is including 2 separate projects:
+ the [./android](./android) project representing a consumer Android codebase.
+ the [./toolbox](./toolbox) project implementing Docker shipped utilities used in the consumer projects.

## Features

+ Custom docker images
+ JavaExec tasks in a composite build (see [./scripts](./scripts))
+ Kotlin script
