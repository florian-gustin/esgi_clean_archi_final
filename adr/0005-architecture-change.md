# 5. Json Adapter

Date: 2023-02-18

## Status

Accepted

## Context

When starting to implement the project with the DDD hexagonal architecture, things were not going as planned. We had to change the architecture because our domain has not a huge complexity but the infrastructure has.

## Decision

The Clean Architecture is the perfect candidate for this orientation.

## Consequences

About the modularity and the futures expansions of the application, there is a high possibility that the console parser and the file task repository will be changed. As instance the console parser will be changed to a web parser and the file task repository will be changed to a database task repository. So we have to be able to change the implementation of the parser and the task repository without changing the use cases.







