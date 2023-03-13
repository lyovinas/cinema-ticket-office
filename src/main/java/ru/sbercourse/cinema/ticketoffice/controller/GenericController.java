package ru.sbercourse.cinema.ticketoffice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbercourse.cinema.ticketoffice.dto.GenericDto;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;
import ru.sbercourse.cinema.ticketoffice.service.GenericService;

import java.util.Set;

public abstract class GenericController<E extends GenericModel, D extends GenericDto> {

    protected GenericService<E, D> service;


    @Operation(summary = "Получить все записи", description = "Позволяет получить полный список записей")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<D>> getAll() {
        Set<D> resultingList = service.getAll();
        return resultingList.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.OK).body(resultingList);
    }

    @Operation(summary = "Получить запись по ID", description = "Позволяет получить одну запись по заданному ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> getById(@PathVariable("id") @Parameter(description = "Идентификатор записи") Long id) {
        D foundDto = service.getById(id);
        return foundDto != null
                ? ResponseEntity.ok(foundDto)
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Создать новую запись", description = "Позволяет создать переданную запись")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> create(@RequestBody D newDto) {
        D createdDto = service.create(newDto);
        return createdDto != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(createdDto)
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @Operation(summary = "Изменить существующую запись",
            description = "Позволяет заменить существующую запись на обновленную")
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> update(@PathVariable("id") @Parameter(description = "Идентификатор записи") Long id,
                                    @RequestBody D toUpdateDto) {
        D updatedDto = service.update(id, toUpdateDto);
        return updatedDto != null
                ? ResponseEntity.ok(updatedDto)
                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @Operation(summary = "Удалить запись по ID", description = "Позволяет удалить запись по заданному ID")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<D> deleteById(@PathVariable("id") @Parameter(required = true,
            description = "Идентификатор записи") Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    public void setService(GenericService<E, D> service) {
        this.service = service;
    }
}
