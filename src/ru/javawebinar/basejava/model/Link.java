package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Link {

    private final String value;
    private final String url;

    public Link(String value, String url) {
        Objects.requireNonNull(value,"Link value must be not null");
        this.value = value;
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        return value.equals(link.value) && (url != null ? url.equals(link.url) : link.url == null);
    }

    @Override
    public String toString() {
        return "Link{" +
                "value='" + value + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }


}
