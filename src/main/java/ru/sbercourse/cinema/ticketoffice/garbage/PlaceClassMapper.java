//package ru.sbercourse.cinema.ticketoffice.garbage;
//
//import org.springframework.stereotype.Component;
//import ru.sbercourse.cinema.ticketoffice.mapper.GenericMapper;
//
//@Component
//public class PlaceClassMapper extends GenericMapper<PlaceClass, PlaceClassDTO> {
////        implements ConverterForSpecificFields<PlaceClass, PlaceClassDto> {
//
////    private SeatRepository seatRepository;
//
//
//    public PlaceClassMapper() {
//        super(PlaceClass.class, PlaceClassDTO.class);
//    }
//
//
////    @PostConstruct
////    @Override
////    public void setupMapper() {
////        modelMapper.createTypeMap(PlaceClass.class, PlaceClassDto.class)
////                .addMappings(m -> m.skip(PlaceClassDto::setSeatsIds))
////                .setPostConverter(toDtoConverter());
////
////        modelMapper.createTypeMap(PlaceClassDto.class, PlaceClass.class)
////                .addMappings(m -> m.skip(PlaceClass::setSeats))
////                .setPostConverter(toEntityConverter());
////    }
////
////    @Override
////    public void mapSpecificFields(PlaceClassDto source, PlaceClass destination) {
////        Set<Long> seatsIds = source.getSeatsIds();
////        if (seatsIds != null) {
////            destination.setSeats(new HashSet<>(seatRepository.findAllById(seatsIds)));
////        } else destination.setSeats(null);
////    }
////
////    @Override
////    public void mapSpecificFields(PlaceClass source, PlaceClassDto destination) {
////        Set<Long> seatsIds = null;
////        Set<Seat> seats = source.getSeats();
////        if (seats != null) {
////            seatsIds = seats.stream()
////                    .map(GenericModel::getId)
////                    .collect(Collectors.toSet());
////        }
////        destination.setSeatsIds(seatsIds);
////    }
////
////
////    @Autowired
////    public void setSeatRepository(SeatRepository seatRepository) {
////        this.seatRepository = seatRepository;
////    }
//}
