# Skip the Dishes

[![Build Status](https://travis-ci.org/bbranquinho/skip-the-dishes.svg?branch=master)](https://travis-ci.org/bbranquinho/skip-the-dishes) [![codecov](https://codecov.io/gh/bbranquinho/skip-the-dishes/branch/master/graph/badge.svg)](https://codecov.io/gh/bbranquinho/skip-the-dishes)

Challenge: develop an API that "Allows Authentication", "Query Products", "Receive Orders", "Cancel an Order", "Get Order Status" and "Store data in a database". Besides these tasks, a solution that supports both scalability and millions of users is required.

## 1. Requirements

Requirements to run this project:

    1. Docker 17.12
    2. Docker-compose 1.18
    3. JDK 1.8 (optional)
    4. Maven 3.4 (optional)

Add in your *Hosts* (**/etc/hosts** for Linux/OS X, and **c:\Windows\System32\drivers\etc\hosts** for Windows) the value `127.0.0.1 keycloak`, it is required for authentication.

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

A Postman collection that can be used for testing purpose is available. This Postman can be found in *docs/postman*. 

Based on unitary tests an API documentation was built using Spring REST Docs and it is available at http://nostalgic-hugle-712220.bitballoon.com or in *docs/api-docs/skip-the-dishes-resources.html*. It is available for each application a Swagger interface at http://keycloak:8081/swagger-ui.html and http://keycloak:8082/swagger-ui.html, to authenticate uses the **username** and **password**, **skip** and **password**, respectively.

Once the application is running all events can be found at http://keycloak:2113, **username** and **password** is **admin** and **changeit** respectively.

For IAM is used the Keycloak and it can be accessed on http://keycloak:8080 with the credential admin/admin.

### 3.1. Overview

|      Application      |                    Hostname                  |   Credential (user)  | Credential (password) |
| --------------------- | -------------------------------------------- | -------------------- | --------------------- |
| API Documentation     | http://nostalgic-hugle-712220.bitballoon.com |         -            |           -           |
| Event Store Database  | http://keycloak:2113                         |       admin          |       changeit        |
| Keycloak              | http://keycloak:8080                         |       admin          |         admin         |
| Command (Swagger)     | http://keycloak:8081/swagger-ui.html         |       skip           |       password        |
| Query (Swagger)       | http://keycloak:8082/swagger-ui.html         |       skip           |       password        |
| PostgreSQL (Keycloak) | keycloak:5432                                | skip_the_dishes_user | skip_the_dishes_pass  |
| PostgreSQL (Query)    | keycloak:5432                                | skip_the_dishes_user | skip_the_dishes_pass  |

## 4. Architecture

Many things are considered for the proposed solution. The following figures show both domain and basic conceptual architecture based on domain modules.

### 4.1. Domain Architecture

The proposed solution uses some concepts such as DDD, Event Sourcing, and CQRS. Some details of these concepts are available in:

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

The following figure shows a basic architecture for future developments to improve the project. Some things are omitted for simplicity, such as Log (Graylog), Container Orchestration (Docker Swarm or Kubernetes), Data Analysis, Monitoring, and so on.

![Basic Architecture](https://user-images.githubusercontent.com/1013619/38308255-dd323868-37ec-11e8-9486-0f228237ec98.png)

## 5. Technologies

The following technologies are used for each "domain module".

    1. Java
    2. Kotlin
    3. Spring Boot
    4. Spring Data JPA
    5. Spring REST Docs
    6. Event Store Database (developed by Greg Young)
    7. PostgreSQL
    8. Liquibase
    9. Mockito
    10. Mockito Kotlin
    11. Feign
    12. Docker
    13. Docker Compose
    14. Keycloak
    15. Swagger
