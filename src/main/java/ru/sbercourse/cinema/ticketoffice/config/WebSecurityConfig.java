//package ru.sbercourse.cinema.ticketoffice.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.security.web.firewall.HttpFirewall;
//import org.springframework.security.web.firewall.StrictHttpFirewall;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import ru.sbercourse.cinema.ticketoffice.service.userdetails.CustomUserDetailsService;
//
//import java.util.Arrays;
//
//import static ru.sbercourse.cinema.ticketoffice.constants.SecurityConstants.*;
//import static ru.sbercourse.cinema.ticketoffice.constants.UserRolesConstants.ADMIN;
//import static ru.sbercourse.cinema.ticketoffice.constants.UserRolesConstants.MANAGER;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//  //https://docs.spring.io/spring-security/reference/servlet/exploits/firewall.html
//  @Bean
//  public HttpFirewall httpFirewall() {
//    StrictHttpFirewall firewall = new StrictHttpFirewall();
//    firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//    return firewall;
//  }
//
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//        .csrf(csrf -> csrf
//            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//        )
//        //Настройка http запросов - кому куда можно/нельзя
//        .authorizeHttpRequests((requests) ->
//            requests
//                .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
//                .requestMatchers(FILMS_WHITE_LIST.toArray(String[]::new)).permitAll()
//                .requestMatchers(DIRECTORS_WHITE_LIST.toArray(String[]::new)).permitAll()
//                .requestMatchers(USERS_WHITE_LIST.toArray(String[]::new)).authenticated()
//                .requestMatchers(FILMS_PERMISSION_LIST.toArray(String[]::new)).hasAnyRole(ADMIN, MANAGER)
//                .requestMatchers(USERS_PERMISSION_LIST.toArray(String[]::new)).hasAnyRole(ADMIN, MANAGER)
//        )
//        .formLogin((form) -> form
//            .loginPage("/login")
//            .defaultSuccessUrl("/")
//            .permitAll()
//        )
//        .logout((logout) -> logout
//            .logoutSuccessUrl("/login")
//            .logoutUrl("/logout")
//            .invalidateHttpSession(true)
//            .deleteCookies("JSESSIONID")
//            .permitAll()
//            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//        );
//    return http.build();
//  }
//
//  @Bean
//  public BCryptPasswordEncoder bCryptPasswordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//
//  @Autowired
//  protected void configureGlobal(AuthenticationManagerBuilder auth, BCryptPasswordEncoder bCryptPasswordEncoder,
//                                 CustomUserDetailsService customUserDetailsService) throws Exception {
//    auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
//  }
//}
