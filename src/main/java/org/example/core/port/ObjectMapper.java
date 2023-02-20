package org.example.core.port;

public interface ObjectMapper<A, B> {

    B toPersistenceObject(A entity);
    A toEntity(B persistenceObject);
}
