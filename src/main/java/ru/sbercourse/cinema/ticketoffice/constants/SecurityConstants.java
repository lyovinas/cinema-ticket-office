package ru.sbercourse.cinema.ticketoffice.constants;

import java.util.List;

public interface SecurityConstants {
  List<String> RESOURCES_WHITE_LIST = List.of(
      "/resources/**",
      "/js/**",
      "/css/**",
      "/",
      "/login",
      "/users/registration",
      "/users/remember-password",
      "/users/change-password",
      "/error",
      // -- Swagger UI v3 (OpenAPI)
      "/swagger-ui/**",
      "/v3/api-docs/**");

  List<String> FILMS_WHITE_LIST = List.of(
      "/films/{id}",
      "/films/search",
//      "/films/search/director",
      "/films"
  );

  List<String> DIRECTORS_WHITE_LIST = List.of(
      "/directors/{id}",
      "/directors/search",
      "/directors"
  );
  List<String> FILMS_PERMISSION_LIST = List.of(
      "/films/add",
      "/films/update",
      "/films/delete"
//      "/publish/get-film/*",
//      "/films/download/*"
  );

  List<String> USERS_PERMISSION_LIST = List.of(
      "/users",
      "/users/add",
      "/users/delete",
      "/users/add-manager"
  );

  List<String> USERS_WHITE_LIST = List.of(
      "/users/profile/{id}",
      "/users/profile/update/{id}"

  );

//  List<String> USERS_REST_WHITE_LIST = List.of("/users/auth");
}
