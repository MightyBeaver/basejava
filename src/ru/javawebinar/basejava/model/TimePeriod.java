package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class TimePeriod {
    private final Link organizationLink;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

    public TimePeriod(String orgName, String url, LocalDate startDate, LocalDate endDate, String description) {
        Objects.requireNonNull(startDate,"Start date must not be null");
        Objects.requireNonNull(description,"Description must not be null");
        this.organizationLink = new Link(orgName,url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Link getOrganizationLink() {
        return organizationLink;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePeriod that = (TimePeriod) o;

        return organizationLink.equals(that.organizationLink)
                && startDate.equals(that.startDate)
                && (endDate != null ? endDate.equals(that.endDate) : that.endDate == null)
                && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = organizationLink.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TimePeriod{" +
                "organization=" + organizationLink.getName() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                '}';
    }
}
