package ru.sbercourse.cinema.ticketoffice.model;

public enum Genre {
    FANTASY("Фантастика"),
    COMEDY("Комедия"),
    DRAMA("Драма"),
    ACTION("Боевик");

    private final String text;

    Genre(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Genre getByText(String text) {
        Genre foundGenre = null;
        for (Genre genre : values()) {
            if (genre.getText().equals(text)) {
                foundGenre = genre;
                break;
            }
        }
        return foundGenre;
    }
}
