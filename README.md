# Skip the Dishes

[![Build Status](https://travis-ci.org/bbranquinho/skip-the-dishes.svg?branch=master)](https://travis-ci.org/bbranquinho/skip-the-dishes) [![codecov](https://codecov.io/gh/bbranquinho/skip-the-dishes/branch/master/graph/badge.svg)](https://codecov.io/gh/bbranquinho/skip-the-dishes)

Challenge: To develop an API that "Allows Authentication", "Query Products", "Receive Orders", "Cancel an Order", "Get Order Status" and "Store data in a database". Besides these tasks a solution supports both scalability and millions of users is required.

## 1. Requirements

Requirements to run the project:

    1. JDK 1.8
    2. Maven 3.4+
    3. Docker 17.12+
    4. Docker-compose 1.18+

## 2. Running

In order to execute the project, it is necessary to run the following commands inside the root folder and add in your **/etc/hosts** the value `127.0.0.1 keycloak` (required for authentication).

```sh
$ mvn clean install
$ docker-compose -f docker-compose-local.yml up --build
```

## 3. Testing

A Postman collection that can be used for testing purpose is available. This Postman can be found in *docs/postman*. 

Based on unitary tests an API documentation was built using Spring REST Docs and it is available at http://nostalgic-hugle-712220.bitballoon.com or in *docs/api-docs/skip-the-dishes-resources.html*. It is available for each application a Swagger interface at http://localhost:8081/swagger-ui.html and http://localhost:8082/swagger-ui.html, to authenticate uses the credential skip/password.

Once the application is running all events can be found at http://localhost:2113

For IAM is used the Keycloak and it can be accessed on http://localhost:8080 with the credentials admin/admin.

## 4. Architecture

Many things are considered for the proposed solution. The following figures show both domain and basic conceptual architecture based on domain modules.

### 4.1. Domain Architecture

The proposed solution uses some concepts such as DDD, Event Sourcing, and CQRS. Some details of these concepts are available in:

  #### DDD: https://martinfowler.com/tags/domain%20driven%20design.html
  #### CQRS: https://cqrs.files.wordpress.com/2010/11/cqrs_documents.pdf
  #### Event Driven: https://martinfowler.com/articles/201701-event-driven.html
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

The following technologies are used for the "domain module" development.

    1. Java
    2. Spring Boot
    3. Spring Data JPA (Spring JDBCTemplate is a better option and it can be used for new developments)
    4. Spring REST Docs
    5. Event Store Database (developed by Greg Young)
    6. PostgreSQL
    7. Liquibase
    8. Kotlin (tests and POJOs)
    9. Mockito
    10. Mockito Kolin
    11. Feign
    12. Docker
    13. Docker Compose
    14. Keycloak

