# spring-data-rest-2279
This repository reproduces https://github.com/spring-projects/spring-data-rest/issues/2276 which is closely related to https://github.com/spring-projects/spring-data-rest/issues/2279

It contains two branches:
 - [main] branch is with spring boot 2.3.12.RELEASE (spring data Neumann-SR9 release train).
 - [broken] branch is with spring boot 2.7.9 (spring data bom 2021.2.8). 

With the main branch you can run `mvn spring-boot:run` and visit http://localhost:8080/rest/ in your browser and it all works fine.
With the broken branch it shows errors.
