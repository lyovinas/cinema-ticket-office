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

    public String getText() {//TODO удалить, если не используется
        return text;
    }

}
