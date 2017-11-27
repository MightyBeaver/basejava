package ru.javawebinar.basejava.model;

import java.util.Map;
import java.util.Objects;

public class Organization {
    private final Link organizationLink;
    private final Map<TimePeriod,String> timePeriods;

    public Organization(String orgName, String url, Map<TimePeriod,String> timePeriods) {
        Objects.requireNonNull(timePeriods,"Time periods must not be null");
        this.organizationLink = new Link(orgName,url);
        this.timePeriods = timePeriods;
    }

    public Link getOrganizationLink() {
        return organizationLink;
    }

    public Map<TimePeriod, String> getTimePeriods() {
        return timePeriods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!organizationLink.equals(that.organizationLink)) return false;
        return timePeriods.equals(that.timePeriods);
    }

    @Override
    public int hashCode() {
        int result = organizationLink.hashCode();
        result = 31 * result + timePeriods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organizationLink=" + organizationLink +
                ", timePeriods=" + timePeriods +
                '}';
    }
}
