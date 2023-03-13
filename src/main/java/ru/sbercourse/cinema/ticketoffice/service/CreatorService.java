package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.CreatorDto;
import ru.sbercourse.cinema.ticketoffice.mapper.CreatorMapper;
import ru.sbercourse.cinema.ticketoffice.model.Creator;
import ru.sbercourse.cinema.ticketoffice.repository.CreatorRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreatorService extends GenericService<Creator, CreatorDto> {

    public CreatorService(CreatorRepository creatorRepository, CreatorMapper creatorMapper) {
        setRepository(creatorRepository);
        setMapper(creatorMapper);
    }


//    public Set<Creator> getAllByIds(Set<Long> ids) {
//        return new HashSet<>(repository.findAllById(ids));
//    }
}
