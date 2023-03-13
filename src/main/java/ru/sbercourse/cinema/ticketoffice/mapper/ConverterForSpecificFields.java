package ru.sbercourse.cinema.ticketoffice.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import ru.sbercourse.cinema.ticketoffice.dto.GenericDto;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;

interface ConverterForSpecificFields<E extends GenericModel, D extends GenericDto> {

    default Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return destination;
        };
    }

    default Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return destination;
        };
    }

    @PostConstruct
    void setupMapper();

    void mapSpecificFields(D source, E destination);

    void mapSpecificFields(E source, D destination);
}
