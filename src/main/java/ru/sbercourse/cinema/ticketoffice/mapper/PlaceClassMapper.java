package ru.sbercourse.cinema.ticketoffice.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.PlaceClassDto;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;
import ru.sbercourse.cinema.ticketoffice.model.PlaceClass;
import ru.sbercourse.cinema.ticketoffice.model.Placement;
import ru.sbercourse.cinema.ticketoffice.repository.PlacementRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PlaceClassMapper extends GenericMapper<PlaceClass, PlaceClassDto> {
//        implements ConverterForSpecificFields<PlaceClass, PlaceClassDto> {

//    private PlacementRepository placementRepository;


    public PlaceClassMapper() {
        super(PlaceClass.class, PlaceClassDto.class);
    }


//    @PostConstruct
//    @Override
//    public void setupMapper() {
//        modelMapper.createTypeMap(PlaceClass.class, PlaceClassDto.class)
//                .addMappings(m -> m.skip(PlaceClassDto::setPlacementsIds))
//                .setPostConverter(toDtoConverter());
//
//        modelMapper.createTypeMap(PlaceClassDto.class, PlaceClass.class)
//                .addMappings(m -> m.skip(PlaceClass::setPlacements))
//                .setPostConverter(toEntityConverter());
//    }
//
//    @Override
//    public void mapSpecificFields(PlaceClassDto source, PlaceClass destination) {
//        Set<Long> placementsIds = source.getPlacementsIds();
//        if (placementsIds != null) {
//            destination.setPlacements(new HashSet<>(placementRepository.findAllById(placementsIds)));
//        } else destination.setPlacements(null);
//    }
//
//    @Override
//    public void mapSpecificFields(PlaceClass source, PlaceClassDto destination) {
//        Set<Long> placementsIds = null;
//        Set<Placement> placements = source.getPlacements();
//        if (placements != null) {
//            placementsIds = placements.stream()
//                    .map(GenericModel::getId)
//                    .collect(Collectors.toSet());
//        }
//        destination.setPlacementsIds(placementsIds);
//    }
//
//
//    @Autowired
//    public void setPlacementRepository(PlacementRepository placementRepository) {
//        this.placementRepository = placementRepository;
//    }
}
