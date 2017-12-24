package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class TimePeriod implements Serializable {
    private static final long serialVersionUID = 1L;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public TimePeriod(LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate,"Start date must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePeriod that = (TimePeriod) o;

        return startDate.equals(that.startDate)
                && (endDate != null ? endDate.equals(that.endDate) : that.endDate == null);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TimePeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
