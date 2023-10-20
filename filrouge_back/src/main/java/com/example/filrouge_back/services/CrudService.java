package com.example.filrouge_back.services;

import java.util.List;
import java.util.UUID;

public interface CrudService<T> {
    T create(T entity);
    T read(UUID id);
    T update(UUID id, T updatedEntity);
    void delete(UUID id);
    List<T> getAll();
}

