# message-alerts

Generated with the [_Knot.x Extension Maven Archetype_](https://github.com/Knotx/knotx-extension-archetype).

Check out the [Knot.x Wiki](https://github.com/Cognifide/knotx/wiki/Knot) for more information
about the Knot concept and APIs used here.


To run the extension:

1. Build module with `mvn clean package`.
2. Download proper version of Knot.x stack for [Knot.x bintray](https://bintray.com/knotx/downloads/distro).
3. Update `conf/application.conf` with new module definition, global variables and `exampleKnot.conf` include.
You will find all config entries in `conf/exampleStack.conf`.
The final config file (`conf/application.conf`) should look like:
```
...
modules = [
  "server=io.knotx.server.KnotxServerVerticle"
  "httpRepo=io.knotx.repository.http.HttpRepositoryConnectorVerticle"
  ...
  "exampleKnot=com.somepackage.knot.example.ExampleKnot"
]
global {
  serverPort = 8092
  ...
}

global {
  exampleKnot {
    address = knotx.knot.example
  }
}

config.exampleKnot {
  options.config {
    include required(classpath("includes/exampleKnot.conf"))
  }
}
...
```
4. Update `server.conf` and alter `defaultFlow` to use example knot in the routing.
The example config may look like:
```
routing.GET.items = [
    {
      path = ".*"
      address = ${global.exampleKnot.address}
      onTransition.next {
        address = ${global.address.knot.service}
        onTransition.next {
          address = ${global.address.knot.hbs}
        }  
      }
    }
    ...
]
```
5. Copy `includes/exampleKnot.conf` to the stack `conf/includes` directory.
6. Copy `target/your-knot-extensions.jar` to `lib` directory in stack instance.
7. Start your Knot.x stack instance.

**Note:** This document explains how to integrate the module with Knot.x Stack. Check
[tutorials](http://knotx.io/tutorials/) to see fully configured examples.
