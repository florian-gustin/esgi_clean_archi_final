# Todo list

This app create your todo list easily in your terminal. The config property is stored in ```~/.consoleagenda```

## Prequisites

- Java 17+
- Maven

## How to build

```bash
mvn clean install
```

## Available arguments

- add: add task
- add-sub : add subtask
- list : list tasks
- remove : remove a task
- update : update a task

## Example

- `agenda add -c myTask`
- `update 1a551020-3683-4165-8d63-1e6af67a0070 -c My Updated task`
- `agenda list`
- `agenda remove 1a551020-3683-4165-8d63-1e6af67a0070 `
- `agenda update 1a551020-3683-4165-8d63-1e6af67a0070 -s done`

## Architecture decision

- [Architecture approach](./adr/0001-architectures-approaches.md)
- [Modeling](./adr/0002-modeling.md)
- [Directory Structure](./adr/0003-directory-structure.md)
- [Json Adapter](./adr/0004-json-adapter.md)
- [Architecture change](./adr/0005-architecture-change.md)
- [Directory Structure](./adr/0006-directory-structure.md)
- [File Task Repository](./adr/0007-file-task-repository.md)

## JSON data

An example of json tasks

```json
{
  "data": [
    {
      "id": "ebd5f0d8-ea8a-44e5-be9d-5d73e059afeb",
      "parentId": null,
      "createdDate": "2023-02-20",
      "dueDate": "2023-01-20",
      "content": "My first task",
      "status": "TODO",
      "tag": null,
      "subTask": []
    },
    {
      "id": "d0faa257-f462-4c71-8dd2-f527883c293a",
      "parentId": null,
      "createdDate": "2023-02-20",
      "dueDate": "2023-02-20",
      "content": "Cook pastas",
      "status": "DONE",
      "tag": null,
      "subTask": []
    },
    {
      "id": "81e3ebdb-da59-4f70-94ce-3cc751966354",
      "parentId": null,
      "createdDate": "2023-02-20",
      "dueDate": "2028-02-20",
      "content": "Create a very big infrastructure for Amazon",
      "status": "TODO",
      "tag": "IT",
      "subTask": []
    }
  ]
}
```