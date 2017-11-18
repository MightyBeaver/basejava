package ru.javawebinar.basejava.model;

public enum Contacts {
    MOBILE_PHONE("Тел."),
    SKYPE("Skype"),
    EMAIL("E-mail"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    HOMEPAGE("Домашняя странца");

    private String title;

    Contacts(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}