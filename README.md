# SkipTheDishes

[![Build Status](https://travis-ci.org/bbranquinho/skip-the-dishes.svg?branch=master)](https://travis-ci.org/bbranquinho/skip-the-dishes) [![codecov](https://codecov.io/gh/bbranquinho/skip-the-dishes/branch/master/graph/badge.svg)](https://codecov.io/gh/bbranquinho/skip-the-dishes)

Project showing an example of Event Sourcing and CQRS patterns. It's available an API that "Allows Authentication", "Query Products", "Receive Orders", "Cancel an Order" and "Get Order Status".

## 1. Requirements

Requirements to run this project:

    1. Docker 17.12
    2. Docker-compose 1.18
    3. JDK 1.8 (optional)
    4. Maven 3.4 (optional)

Add in your *Hosts* (**/etc/hosts** for Linux/OS X, and **c:\Windows\System32\drivers\etc\hosts** for Windows) the value `127.0.0.1 keycloak` (required for authentication).

## 2. Running

There are two ways to execute the project.

### 2.1. First (Linux, OS X)

In order to execute the project based on docker, that not requires the JDK and Maven, it is necessary to run the following commands inside the root folder:

```sh
$ ./start-local.sh
```

### 2.2. Second (Linux, OS X, Windows)

This option is better, however, JDK and Maven are required.

```sh
$ mvn package
$ docker-compose -f docker-compose-local.yml up --build
```

## 3. Testing

There is a Postman collection that can be used for testing and it's available in *docs/postman*. 

Based on unitary tests an API documentation was built using Spring REST Docs and it's available at http://nostalgic-hugle-712220.bitballoon.com or *docs/api-docs/skip-the-dishes-resources.html*.

For each each application there is a Swagger interface http://keycloak:8081/swagger-ui.html and http://keycloak:8082/swagger-ui.html (**username** = **skip**)(**password** = **password**).

All events can be found at http://keycloak:2113/web/index.html#/streams (**username** = **admin**)(**password** = **changeit**).

The Keycloak was used for the IAM http://keycloak:8080 (**username** = **admin**)(**password** = **admin**).

### 3.1. Overview

|      Application      |             Hostname (folder)                |   Credential (user)  | Credential (password) |
| --------------------- | -------------------------------------------- | -------------------- | --------------------- |
| API Documentation     | Folder docs/api-docs                         |         -            |           -           |
| Event Store Database  | http://keycloak:2113                         |       admin          |       changeit        |
| Keycloak              | http://keycloak:8080                         |       admin          |         admin         |
| Command (Swagger)     | http://keycloak:8081/swagger-ui.html         |       skip           |       password        |
| Query (Swagger)       | http://keycloak:8082/swagger-ui.html         |       skip           |       password        |
| PostgreSQL (Keycloak) | keycloak:5432                                | skip_the_dishes_user | skip_the_dishes_pass  |
| PostgreSQL (Query)    | keycloak:5432                                | skip_the_dishes_user | skip_the_dishes_pass  |

## 4. Architecture

Many things are considered for the proposed solution. The following figures show both domain and basic conceptual architecture based on domain modules.

### 4.1. Domain Architecture

The proposed solution follow some concepts such as DDD, Event Sourcing, and CQRS. Some details of these concepts are available in:

  #### DDD: https://martinfowler.com/tags/domain%20driven%20design.html
  #### CQRS: https://cqrs.files.wordpress.com/2010/11/cqrs_documents.pdf
  #### Event Driven: https://martinfowler.com/articles/201701-event-driven.html and https://www.youtube.com/watch?v=STKCRSUsyP0
  #### Event Sourcing: https://www.youtube.com/watch?v=cISNDnwlSgw
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

![Domain Architecture](https://user-images.githubusercontent.com/1013619/42735343-86290196-8828-11e8-8edf-693ea75666a3.png)

### 4.2. Basic Architecture

The following figure show a basic architecture for future developments to improve the project. Some things are omitted for simplicity, such as Log (Graylog), Container Orchestration (Docker Swarm or Kubernetes), Data Analysis, Monitoring, and so on.

![Basic Architecture](https://user-images.githubusercontent.com/1013619/38308255-dd323868-37ec-11e8-9486-0f228237ec98.png)

## 5. Technologies

The following technologies are used for each "domain module".

    1. Kotlin
    2. Event Store Database (developed by Greg Young): https://eventstore.org/
    3. Spring Boot
    4. Docker
    5. Docker Compose
    6. Keycloak
    7. Spring Data JPA
    8. Spring REST Docs
    9. PostgreSQL
    10. Liquibase
    11. Mockito
    12. Mockito Kotlin
    13. Feign
    14. Swagger
    15. H2
