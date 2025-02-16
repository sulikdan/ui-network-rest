# ui-network-rest

## Intellij run-config
See folder `.run`, where is intellij run config saved.

## Helpful links to open
* [Swagger](http://localhost:8087/swagger-ui/index.html)
* [H2-console](http://localhost:8087/h2-console/)

## Issues to discuss
* How do you differentiate deployment? There was no mention of deployment
  * I will assume that device without uplink is top of tree and therefore root of network deployment
  * In reality it would be seperate entities Device and Deployment, whereas deployment would have link to root device or devices
* Deployment depth selection - not mentioned and its hard to validate/limit what can be done
  * cycles - if user create cycle, we will pass data in infinite loop
  * limit retrieved depth - could save resources + prevent infinite loop
* 
