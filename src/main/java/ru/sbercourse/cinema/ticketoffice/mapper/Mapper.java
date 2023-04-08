package ru.sbercourse.cinema.ticketoffice.mapper;

import ru.sbercourse.cinema.ticketoffice.dto.GenericDTO;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {

  E toEntity(D dto);

  D toDTO(E entity);

  List<D> toDTOs(List<E> entities);
}
