package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.sbercourse.cinema.ticketoffice.dto.GenericDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.Mapper;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;
import ru.sbercourse.cinema.ticketoffice.repository.GenericRepository;

import java.time.LocalDateTime;
import java.util.List;

public abstract class GenericService<E extends GenericModel, D extends GenericDTO> {

    protected GenericRepository<E> repository;
    protected Mapper<E, D> mapper;



    public List<D> getAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public Page<D> getAll(Pageable pageable) {
        Page<E> page = repository.findAll(pageable);
        List<D> result = mapper.toDTOs(page.getContent());
        return new PageImpl<>(result, pageable, page.getTotalElements());
    }

    public D getById(Long id) {
        return mapper.toDTO(repository.findById(id).orElse(null));
    }

    public D create(D DTO) {
        if (DTO == null) return null;
        E entity = mapper.toEntity(DTO);
        Long entityId = entity.getId();
        if (entityId != null && repository.existsById(entityId)) {
            return null;
        }
        entity.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        entity.setCreatedWhen(LocalDateTime.now());
        return mapper.toDTO(repository.save(entity));
    }

    public D update(D DTO) {
        E entity = mapper.toEntity(DTO);
        Long id = entity.getId();
        if (id != null) {
            E existingEntity = repository.findById(id).orElse(null);
            if (existingEntity != null) {
                entity.setCreatedWhen(existingEntity.getCreatedWhen());
                entity.setCreatedBy(existingEntity.getCreatedBy());
                entity.setDeleted(existingEntity.isDeleted());
                entity.setDeletedWhen(existingEntity.getDeletedWhen());
                entity.setDeletedBy(existingEntity.getDeletedBy());
                entity.setUpdatedWhen(LocalDateTime.now());
                entity.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
                return mapper.toDTO(repository.save(entity));
            }
        }
        return null;
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public void softDelete(Long id) {
        D DTO = getById(id);
        DTO.setDeleted(true);
        DTO.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        DTO.setDeletedWhen(LocalDateTime.now());
        repository.save(mapper.toEntity(DTO));
    }

    public void restore(Long id) {
        D DTO = getById(id);
        DTO.setDeleted(false);
        DTO.setDeletedBy(null);
        DTO.setDeletedWhen(null);
        repository.save(mapper.toEntity(DTO));
    }
}
