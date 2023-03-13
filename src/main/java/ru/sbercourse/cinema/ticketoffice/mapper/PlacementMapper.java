package ru.sbercourse.cinema.ticketoffice.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbercourse.cinema.ticketoffice.dto.PlacementDto;
import ru.sbercourse.cinema.ticketoffice.model.GenericModel;
import ru.sbercourse.cinema.ticketoffice.model.Order;
import ru.sbercourse.cinema.ticketoffice.model.PlaceClass;
import ru.sbercourse.cinema.ticketoffice.model.Placement;
import ru.sbercourse.cinema.ticketoffice.repository.OrderRepository;
import ru.sbercourse.cinema.ticketoffice.repository.PlaceClassRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PlacementMapper extends GenericMapper<Placement, PlacementDto>
        implements ConverterForSpecificFields<Placement, PlacementDto> {

    private PlaceClassRepository placeClassRepository;
//    private OrderRepository orderRepository;


    public PlacementMapper() {
        super(Placement.class, PlacementDto.class);
    }


    @PostConstruct
    @Override
    public void setupMapper() {
        modelMapper.createTypeMap(Placement.class, PlacementDto.class)
                .addMappings(m -> {
                    m.skip(PlacementDto::setPlaceClassId);
//                    m.skip(PlacementDto::setOrdersIds);
                })
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(PlacementDto.class, Placement.class)
                .addMappings(m -> {
                    m.skip(Placement::setPlaceClass);
//                    m.skip(Placement::setOrders);
                })
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(PlacementDto source, Placement destination) {
        //noinspection DuplicatedCode
        Long placeClassId = source.getPlaceClassId();
        if (placeClassId != null) {
            destination.setPlaceClass(placeClassRepository.findById(placeClassId).orElse(null));
        } else destination.setPlaceClass(null);

//        Set<Long> ordersIds = source.getOrdersIds();
//        if (ordersIds != null) {
//            destination.setOrders(new HashSet<>(orderRepository.findAllById(ordersIds)));
//        } else destination.setOrders(null);
    }

    @Override
    public void mapSpecificFields(Placement source, PlacementDto destination) {
        Long placeClassId = null;
        PlaceClass placeClass = source.getPlaceClass();
        if (placeClass != null) {
            placeClassId = placeClass.getId();
        }
        destination.setPlaceClassId(placeClassId);

        //noinspection DuplicatedCode
//        Set<Long> ordersIds = null;
//        Set<Order> orders = source.getOrders();
//        if (orders != null) {
//            ordersIds = orders.stream()
//                    .map(GenericModel::getId)
//                    .collect(Collectors.toSet());
//        }
//        destination.setOrdersIds(ordersIds);
    }


    @Autowired
    public void setPlaceClassRepository(PlaceClassRepository placeClassRepository) {
        this.placeClassRepository = placeClassRepository;
    }

//    @Autowired
//    public void setOrderRepository(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
}
