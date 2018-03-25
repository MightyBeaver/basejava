package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable{
    private static final long serialVersionUID = 1L;
    private String value;
    private String url;

    public Link() {
    }

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

        if (!value.equals(link.value)) return false;
        return url != null ? url.equals(link.url) : link.url == null;
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
