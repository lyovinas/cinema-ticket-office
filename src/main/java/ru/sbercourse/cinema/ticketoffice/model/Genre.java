package ru.sbercourse.cinema.ticketoffice.model;

enum Genre {
    FANTASY("Фантастика"),
    COMEDY("Комедия"),
    DRAMA("Драма"),
    ACTION("Боевик");

    private final String genreText;

    Genre(String text) {
        genreText = text;
    }

    public String getGenreText() {
        return genreText;
    }
}
