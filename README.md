# Skip the Dishes - Vanhack Hackathon

[![Build Status](https://travis-ci.org/bbranquinho/skip-the-dishes.svg?branch=master)](https://travis-ci.org/bbranquinho/skip-the-dishes) [![codecov](https://codecov.io/gh/bbranquinho/skip-the-dishes/branch/master/graph/badge.svg)](https://codecov.io/gh/bbranquinho/skip-the-dishes)

Challenge: Develop an API that "Allow Authentication", "Query Products", "Receive Orders", "Cancel an Order", "Get Order Status" and "Store data in a database". Besides these tasks is required that the solution support scalability and in few months support millions of users.  
  
### **Observation: I'm so sorry, but I spent more time than expected to solve a problem during the challenge, for such reason I could not finish the task of authentication.** 

## 1. Requirements

Requirements to run the project:

    1. JDK 1.8
    2. Maven 3.4+
    3. Docker 17.12+
    4. Docker-compose 1.18+

## 2. Build and Run

In order to execute the project, it is necessary to run the following commands inside the root folder.

```sh
$ mvn clean install
$ docker-compose -f docker-compose-local.yml up
```

## 3. Testing

It is available a collection on Postman that can be used for testing purpose, this Postman can be found on folder *docs/postman*.

## 4. Architecture

Many things are considered for the proposed solution, the following figure shows all modules and their relations.

![Basic Architecture](https://user-images.githubusercontent.com/1013619/37637807-8a7c911e-2be8-11e8-9a09-71091317f36a.png)

The proposed solution uses some concepts like DDD, Event Sourcing, and CQRS. Some details of these concepts are available in:

    **DDD**: https://martinfowler.com/bliki/CQRS.html
    **Event Sourcing**: https://martinfowler.com/eaaDev/EventSourcing.html
    **CQRS**: https://martinfowler.com/bliki/CQRS.html

## 5. Technologies

The following technologies are used for the basic architecture: 

    1. Java
    2. Spring Boot
    3. Event Store Database (developed by Greg Young)
    4. PostgreSQL
    5. Liquibase
    6. Mockito
    7. Mockito Kolin
    8. Kotlin
    9. Feign

