package ru.sbercourse.cinema.ticketoffice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;
import ru.sbercourse.cinema.ticketoffice.repository.GenericRepository;

import java.util.List;

public abstract class GenericController<T extends GenericModel> {
    private GenericRepository<T> repository;


    @Operation(summary = "Получить все записи", description = "Позволяет получить полный список записей")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> getAll() {
        List<T> resultingList = repository.findAll();
        return resultingList.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.OK).body(resultingList);
    }

    @Operation(summary = "Получить запись по ID", description = "Позволяет получить одну запись по заданному ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getById(@PathVariable("id") @Parameter(description = "Идентификатор записи") Long id) {
        return repository.findById(id).map(e -> ResponseEntity.status(HttpStatus.OK).body(e))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Создать новую запись", description = "Позволяет создать переданную запись")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> create(@RequestBody T newEntity) {
        return newEntity.getId() != null && repository.existsById(newEntity.getId()) ?
                ResponseEntity.status(HttpStatus.CONFLICT).build()
                : ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newEntity));
    }

    @Operation(summary = "Изменить существующую запись",
            description = "Позволяет заменить существующую запись на обновленную")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> update(@RequestBody T updatedEntity) {
        return updatedEntity.getId() != null && repository.existsById(updatedEntity.getId()) ?
                ResponseEntity.ok(repository.save(updatedEntity))
                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @Operation(summary = "Удалить запись по ID", description = "Позволяет удалить запись по заданному ID")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<T> deleteById(@PathVariable("id") @Parameter(description = "Идентификатор записи") Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }


    protected void setRepository(GenericRepository<T> repository) {
        this.repository = repository;
    }
}
