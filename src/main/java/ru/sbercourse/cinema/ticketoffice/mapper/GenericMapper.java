package ru.sbercourse.cinema.ticketoffice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbercourse.cinema.ticketoffice.dto.GenericDto;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class GenericMapper<E extends GenericModel, D extends GenericDto> implements Mapper<E, D> {

  protected ModelMapper modelMapper;
  private final Class<E> ENTITY_CLASS;
  private final Class<D> DTO_CLASS;


  protected GenericMapper(Class<E> ENTITY_CLASS, Class<D> DTO_CLASS) {
    this.ENTITY_CLASS = ENTITY_CLASS;
    this.DTO_CLASS = DTO_CLASS;
  }


  @Override
  public E toEntity(D dto) {
    return dto == null
        ? null
        : modelMapper.map(dto, ENTITY_CLASS);
  }

//  @Override
//  public List<E> toEntities(List<D> dtos) {
//    return dtos.stream().map(this::toEntity).toList();
//  }

  @Override
  public D toDto(E entity) {
    return entity == null
        ? null
        : modelMapper.map(entity, DTO_CLASS);
  }

  @Override
  public Set<D> toDtos(Set<E> entities) {
    return entities.stream().map(this::toDto).collect(Collectors.toSet());
  }


  @Autowired
  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
