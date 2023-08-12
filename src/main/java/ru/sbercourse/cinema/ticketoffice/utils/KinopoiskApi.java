package ru.sbercourse.cinema.ticketoffice.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class KinopoiskApi {
    private final String BASE_URL = "https://api.kinopoisk.dev";
    private final String HEADER_AUTH_NAME = "X-API-KEY";
    @Value("${kinopoisk.api.token}")
    private String token;



    public Float getRating(String title, String year, String country, RatingType ratingType) {
        final String METHOD_URL = "/v1.3/movie?selectFields=rating&limit=1&type=movie" +
                "&name=" + URLEncoder.encode(title, StandardCharsets.UTF_8) +
                "&year=" + year + "&countries.name=" + URLEncoder.encode(country, StandardCharsets.UTF_8);
        try {
            HttpResponse response = Request.Get(BASE_URL.concat(METHOD_URL))
                    .addHeader(HEADER_AUTH_NAME, token)
                    .addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .execute().returnResponse();
            if (response.getStatusLine().getStatusCode() == 200) {
                InputStream inputStream = response.getEntity().getContent();
                String jsonString = new BufferedReader(new InputStreamReader(inputStream)).readLine();
                JsonNode movies = new ObjectMapper().readTree(jsonString).get("docs");
                if (movies.size() > 0) {
                    return movies.get(0).get("rating").get(ratingType.getText()).floatValue();
                } else {
                    log.info("Kinopoisk API - movie not found. Method getRating({}, {}, {}, {}).",
                            title, year, country, ratingType.getText());
                }
            } else {
                log.error("Kinopoisk API rating request failed. Method getRating({}, {}, {}, {}). Status: {}",
                        title, year, country, ratingType.getText(), response.getStatusLine());
            }
        } catch (IOException e) {
            log.error("Kinopoisk API rating request failed. Method getRating({}, {}, {}, {}). Exception: {}",
                    title, year, country, ratingType.getText(), e.getMessage());
        }
        return null;
    }


    public enum RatingType {
        KP("kp"),
        IMDB("imdb"),
        TMDB("tmdb"),
        FILM_CRITICS("filmCritics"),
        RUSSIAN_FILM_CRITICS("russianFilmCritics"),
        AWAIT("await");
        private final String text;

        RatingType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
