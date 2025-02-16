# ui-network-rest
A **prototype** of an application to work with network devices. 

## Configuration
* Java 21
* H2 - in-memory

## Intellij run-config
See folder `.run`, where is intellij run config saved.

## Maven wrapper
Is included, tested and also run.

## Helpful links to open
* [Swagger](http://localhost:8087/swagger-ui/index.html)
* [H2-console](http://localhost:8087/h2-console/)

## Automatic data load
When starting the application, there is `import.sql` containing device data to have some sort of populated application.

## Issues to discuss
* How do you differentiate deployment? There was no mention of deployment
  * I will assume that device without uplink is top of tree and therefore root of network deployment
  * In reality it would be seperate entities Device and Deployment, whereas deployment would have link to root device or devices
* Deployment depth selection - not mentioned and its hard to validate/limit what can be done
  * cycles - if user create cycle, we will pass data in infinite loop
  * limit retrieved depth - could save resources + prevent infinite loop
