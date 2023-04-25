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
        return new PageImpl<>(mapper.toDTOs(page.getContent()), pageRequest, page.getTotalElements());
    }

    public List<SeatDTO> getAllByIds(Set<Long> seatIds) {
        List<Seat> seats = repository.findAllById(seatIds)
                .stream().sorted((s1, s2) -> {
                    if (s1.getRow() > s2.getRow()) return 1;
                    if (s1.getRow() < s2.getRow()) return -1;
                    return s1.getPlace().compareTo(s2.getPlace());
                }).toList();
        return mapper.toDTOs(seats);
    }
}
