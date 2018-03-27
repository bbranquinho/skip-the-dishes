# Skip the Dishes - Vanhack Hackathon

[![Build Status](https://travis-ci.org/bbranquinho/skip-the-dishes.svg?branch=master)](https://travis-ci.org/bbranquinho/skip-the-dishes) [![codecov](https://codecov.io/gh/bbranquinho/skip-the-dishes/branch/master/graph/badge.svg)](https://codecov.io/gh/bbranquinho/skip-the-dishes)

Challenge: Develop an API that "Allow Authentication", "Query Products", "Receive Orders", "Cancel an Order", "Get Order Status" and "Store data in a database". Besides these tasks is required that the solution support scalability and in few months support millions of users.  
  
### **Observation: I'm so sorry, but I spent more time than expected to solve a problem during the challenge, for such reason I could not finish the task of authentication. For vanhackaton uses the "vanhackaton" branch.** 

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

## 3. Test and Documentation

It is available a collection on Postman that can be used for testing purpose, this Postman can be found on folder *docs/postman*. 

Based on unitary tests an API documentation based on Spring REST Docs is in available in http://relaxed-bartik-5077aa.bitballoon.com/ and *docs/resources/skip-the-dishes-resources.html*.

## 4. Architecture

Many things are considered for the proposed solution, the following figures show a domain architecture and a basic conceptual architecture based on domain modules.

### 4.1. Domain Architecture

The proposed solution uses some concepts like DDD, Event Sourcing, and CQRS. Some details of these concepts are available in:

  #### DDD: https://martinfowler.com/tags/domain%20driven%20design.html
  #### CQRS: https://martinfowler.com/bliki/CQRS.html
  #### Event Sourcing: https://martinfowler.com/eaaDev/EventSourcing.html and https://www.youtube.com/watch?v=cISNDnwlSgw
    Benefits
        History based queries
        Audit log by design
        Immutability
        User intent
        Decoupling
        Resilience
    Challenges
        Complexity
        Snapshots
        Upcasting
        Race conditions
        Event contracts
        Eventual consistency

![Domain Architecture](https://user-images.githubusercontent.com/1013619/37637807-8a7c911e-2be8-11e8-9a09-71091317f36a.png)

### 4.2. Basic Architecture

The following figure shows a basic architecture for future developments to improve the project. Some things are omitted for simplicity, like Log (Graylog), Container Orchestration (Docker Swarm or Kubernetes), Data Analysis, Monitoring, and so on.

![Basic Architecture](https://user-images.githubusercontent.com/1013619/37944917-0263b764-3154-11e8-830c-5644490113f1.png)

## 5. Technologies

The following technologies are used for the "domain module" development.

    1. Java
    2. Spring Boot
    3. Spring Data JPA (Spring JDBCTemplate is a better option and can be used for new developments)
    4. Spring REST Docs
    5. Event Store Database (developed by Greg Young)
    6. PostgreSQL
    7. Liquibase
    8. Mockito
    9. Mockito Kolin
    10. Kotlin
    11. Feign
