package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private static final long serialVersionUID = 1L;
    private List<String> paragraphs;

    public ListSection() {
    }

    public ListSection(List<String> paragraphs) {
        Objects.requireNonNull(paragraphs,"Paragraphs must not be null");
        this.paragraphs =  paragraphs;
    }

    public List<String> getParagraphs() {
        return paragraphs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return paragraphs.equals(that.paragraphs);
    }

    @Override
    public int hashCode() {
        return paragraphs.hashCode();
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "paragraphs=" + paragraphs +
                '}';
    }
}
