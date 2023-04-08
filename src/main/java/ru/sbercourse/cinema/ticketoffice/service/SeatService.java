package ru.sbercourse.cinema.ticketoffice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.sbercourse.cinema.ticketoffice.dto.SeatsMapDTO;
import ru.sbercourse.cinema.ticketoffice.dto.SeatDTO;
import ru.sbercourse.cinema.ticketoffice.mapper.SeatMapper;
import ru.sbercourse.cinema.ticketoffice.model.Seat;
import ru.sbercourse.cinema.ticketoffice.repository.SeatRepository;

import java.util.*;

@Service
public class SeatService extends GenericService<Seat, SeatDTO> {

    public SeatService(SeatRepository seatRepository, SeatMapper seatMapper) {
        repository = seatRepository;
        mapper = seatMapper;
    }



    public SeatDTO getByRowAndPlace(Byte row, Byte place) {
        return mapper.toDTO(((SeatRepository) repository).getByRowAndPlace(row, place));
    }

    public Map<Byte, Map<Byte, Long>> getAllInMap(Long filmSessionId) {
        List<SeatsMapDTO> seats = ((SeatRepository) repository).getSeatsMap(filmSessionId);
        Map<Byte, Map<Byte, Long>> seatsInMap = new TreeMap<>();
        Map<Byte, Long> places;
        for (SeatsMapDTO seat : seats) {
            places = seatsInMap.get(seat.getRow());
            if (places == null) {
                places = new TreeMap<>();
                places.put(seat.getPlace(), (seat.getOrderId() != null || seat.isDeleted()) ? 0 : seat.getSeatId());
                seatsInMap.put(seat.getRow(), places);
            } else places.put(seat.getPlace(), (seat.getOrderId() != null || seat.isDeleted()) ? 0 : seat.getSeatId());
        }
        return seatsInMap;
    }

    public Page<SeatDTO> searchSeats(Byte rowForSearch, Byte placeForSearch, PageRequest pageRequest) {
        String row = rowForSearch != null ? String.valueOf(rowForSearch) : "%";
        String place = placeForSearch != null ? String.valueOf(placeForSearch) : "%";
        Page<Seat> page = ((SeatRepository) repository).getAllByRowAndPlace(row, place, pageRequest);
//        List<SeatDTO> result;
//        long pageTotalElements;
//        if (rowForSearch != null && placeForSearch == null) {
//            page = ((SeatRepository) repository).getAllByRow(rowForSearch, pageRequest);
//            result = mapper.toDTOs(page.getContent());
//            pageTotalElements = page.getTotalElements();
//        } else if (rowForSearch == null && placeForSearch != null) {
//            page = ((SeatRepository) repository).getAllByPlace(placeForSearch, pageRequest);
//            result = mapper.toDTOs(page.getContent());
//            pageTotalElements = page.getTotalElements();
//        } else {
//            Seat seat = ((SeatRepository) repository).getByRowAndPlace(rowForSearch, placeForSearch);
//            result = List.of(mapper.toDTO(seat));
//            pageTotalElements = 1L;
//        }
        return new PageImpl<>(mapper.toDTOs(page.getContent()), pageRequest, page.getTotalElements());
    }

    public List<SeatDTO> getAllByIds(Set<Long> seatIds) {
        return mapper.toDTOs(repository.findAllById(seatIds));
    }
}
