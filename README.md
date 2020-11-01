# Spring Boot Graphql Jwt Auth Example
> This is Spring Boot Graphql Jwt Auth Example
>
<img src="https://github.com/susimsek/spring-boot-graphql-jwt-auth-ex/blob/main/images/spring-boot-graphql-jwt-auth-ex.png" alt="Spring Boot Graphql Jwt Auth" width="100%" height="100%"/> 

## Prerequisites

* Docker 19.03.x
* Docker Compose 1.25.x

## Installation

```sh
docker-compose up -d
```


## Used Technologies

* Spring Boot 2.3.4
* Postgresql
* Spring Boot Graphql
* Graphql Java Tools
* Spring Boot Playground
* Spring Boot Web
* Spring Boot Jpa
* Spring Boot Security
* Spring Boot Validation
* Spring Boot Actuator
* Model Mapper
* Jjwt
* Lombok
* Dev Tools

## Playground

> You can access the playground ui from the following url.

http://localhost:9090/api/playground

<img src="https://github.com/susimsek/spring-boot-graphql-jwt-auth-ex/blob/main/images/playground.png" alt="Spring Boot Graphiql Jwt Auth" width="100%" height="100%"/>

## Testing

The backend provides a [GraphQL Playground](https://github.com/prisma-labs/graphql-playground) to test things out. The playground is located at http://localhost:9090/api/playground by default.

The graphql server is located at http://localhost:9090/api/graphql by default.

Authentication happens by passing a [JWT token](https://jwt.io/) as a header by using the `Authorization: Bearer <token>` header.

A JWT token can be obtained by calling the `login` mutation. By default, there is a user created with the following credentials:

- Username: admin
- Password: P@ssword
