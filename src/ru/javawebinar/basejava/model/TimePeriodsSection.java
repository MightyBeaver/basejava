package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class TimePeriodsSection extends Section {
    private final List<TimePeriod> timePeriods;

    public TimePeriodsSection(List<TimePeriod> timePeriods) {
        Objects.requireNonNull(timePeriods,"Time periods must not be null");
        this.timePeriods = timePeriods;
    }

    public List<TimePeriod> getTimePeriods() {
        return timePeriods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePeriodsSection that = (TimePeriodsSection) o;

        return timePeriods.equals(that.timePeriods);
    }

    @Override
    public int hashCode() {
        return timePeriods.hashCode();
    }

    @Override
    public String toString() {
        return "TimePeriodsSection{" +
                "timePeriods=" + timePeriods +
                '}';
    }
}
