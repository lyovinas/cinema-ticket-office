package ru.sbercourse.cinema.ticketoffice.constants;

import java.util.List;

public interface SecurityConstants {
  List<String> RESOURCES_WHITE_LIST = List.of(
      "/resources/**",
//      "/js/**",
      "/css/**",
      "/img/**",
      "/",
      "/login",
      "/users/registration",
//      "/users/remember-password",
//      "/users/change-password",
//      "/error",
      // -- Swagger UI v3 (OpenAPI)
      "/swagger-ui/**",
      "/v3/api-docs/**");


  List<String> FILMS_WHITE_LIST = List.of(
      "/films/get/{id}"
  );

  List<String> FILMS_PERMISSION_LIST = List.of(
      "/films",
      "/films/add",
      "/films/update",
      "/films/update/{id}",
      "/films/delete/{id}",
      "/films/restore/{id}",
      "/films/{filmId}/addFilmCreator",
      "/films/addFilmCreator",
      "/films/{filmId}/deleteFilmCreator/{filmCreatorId}",
      "/films/search"
  );


  List<String> FILM_CREATORS_PERMISSION_LIST = List.of(
      "/filmCreators",
//      "/filmCreators/{id}",
      "/filmCreators/add",
      "/filmCreators/update",
      "/filmCreators/update/{id}",
      "/filmCreators/delete/{id}",
      "/filmCreators/restore/{id}",
      "/filmCreators/search"
  );

  List<String> FILM_SESSIONS_PERMISSION_LIST = List.of(
      "/filmSessions/add",
      "/filmSessions/update",
      "/filmSessions/update/{id}",
      "/filmSessions/delete/{id}",
      "/filmSessions/restore/{id}"
  );


  List<String> ORDERS_WHITE_LIST = List.of(
      "/orders/get/{id}",
      "/orders/user/{userId}",
      "/orders/preview",
      "/orders/add",
      "/orders/purchase",
      "/orders/delete/{id}"
  );

  List<String> ORDERS_PERMISSION_LIST = List.of(
      "/orders",
      "/orders/results",
      "/orders/restore/{id}"
  );


  List<String> REVIEWS_WHITE_LIST = List.of(
      "/reviews/add",
      "/reviews/add/{filmId}"
  );

  List<String> REVIEWS_PERMISSION_LIST = List.of(
      "/reviews/delete/{id}",
      "/reviews/restore/{id}"
  );


  List<String> SEATS_WHITE_LIST = List.of(
      "/seats/selectSeat"
  );

  List<String> SEATS_PERMISSION_LIST = List.of(
      "/seats",
      "/seats/add",
      "/seats/delete/{id}",
      "/seats/restore/{id}",
      "/seats/update",
      "/seats/update/{id}",
      "/seats/search"
  );


  List<String> USERS_WHITE_LIST = List.of(
      "/users/profile/{id}",
      "/users/profile/update",
      "/users/profile/update/{id}"
  );

  List<String> USERS_PERMISSION_LIST = List.of(
      "/users",
//      "/users/ban/{id}",
//      "/users/unban/{id}",
      "/users/add-manager"
  );
}
