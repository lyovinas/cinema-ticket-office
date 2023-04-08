package ru.sbercourse.cinema.ticketoffice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbercourse.cinema.ticketoffice.dto.GenericDTO;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;

import java.util.List;

public abstract class GenericMapper<E extends GenericModel, D extends GenericDTO>
        implements Mapper<E, D> {

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

    @Override
    public D toDTO(E entity) {
        return entity == null
                ? null
                : modelMapper.map(entity, DTO_CLASS);
    }

    @Override
    public List<D> toDTOs(List<E> entities) {
        return entities.stream().map(this::toDTO).toList();
    }



    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
