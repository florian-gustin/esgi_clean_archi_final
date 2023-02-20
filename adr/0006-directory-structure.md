# 6. Directory Structure

Date: 2023-02-19

## Status

Accepted

## Context

As we decided to go finally with the Clean architecture, we must define the directory structure again.

## Decision

We will use the following directory structure:

- core:

  - entity
  - port
  - state
  - usecases
  - validation
  - exception

- infrastructure:
  - adapter
  - config
  - io
  - repository

## Consequences

There is some refactoring work to do.





