package ru.sbercourse.cinema.ticketoffice.mapper;

import ru.sbercourse.cinema.ticketoffice.dto.GenericDto;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;

import java.util.Set;

public interface Mapper<E extends GenericModel, D extends GenericDto> {

  E toEntity(D dto);

//  List<E> toEntities(List<D> dtos);

  D toDto(E entity);

  Set<D> toDtos(Set<E> entities);
}
