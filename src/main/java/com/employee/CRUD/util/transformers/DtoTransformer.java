package com.employee.CRUD.util.transformers;

import java.util.List;
import java.util.Set;

public interface DtoTransformer<A, B> {
    B toDto(A entity);

    A toEntity(B dto);

    Set<B> toDtoSet(Set<A> entityList);

    Set<A> toEntitySet(Set<B> dtoList);

    List<B> toDtoList(List<A> entityList);

    List<A> toEntityList(List<B> dtoList);
}
