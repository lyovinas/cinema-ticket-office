package ru.sbercourse.cinema.ticketoffice.service;

import ru.sbercourse.cinema.ticketoffice.dto.GenericDto;
import ru.sbercourse.cinema.ticketoffice.mapper.Mapper;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;
import ru.sbercourse.cinema.ticketoffice.repository.GenericRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class GenericService<E extends GenericModel, D extends GenericDto> {

    protected GenericRepository<E> repository;
    protected Mapper<E, D> mapper;



    public Set<D> getAll() {
        return mapper.toDtos(new HashSet<>(repository.findAll()));
    }

    public D getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    public D create(D DTO) {
        E entity = mapper.toEntity(DTO);
        Long entityId = entity.getId();
        if (entityId != null && repository.existsById(entityId)) {
            return null;
        }
        entity.setCreatedBy("ADMIN");
        entity.setCreatedWhen(LocalDateTime.now());
        return mapper.toDto(repository.save(entity));
    }

    public D update(Long id, D DTO) {
        E entity = mapper.toEntity(DTO);

        if (id != null) {
            E existingEntity = repository.findById(id).orElse(null);
            if (existingEntity != null) {
                entity.setId(id);
                entity.setCreatedWhen(existingEntity.getCreatedWhen());
                entity.setCreatedBy(existingEntity.getCreatedBy());
                return mapper.toDto(repository.save(entity));
            }
        }
        return null;
    }

    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }



    public void setRepository(GenericRepository<E> repository) {
        this.repository = repository;
    }

    public void setMapper(Mapper<E, D> mapper) {
        this.mapper = mapper;
    }

    public Mapper<E, D> getMapper() {
        return mapper;
    }

    public GenericRepository<E> getRepository() {
        return repository;
    }
}
