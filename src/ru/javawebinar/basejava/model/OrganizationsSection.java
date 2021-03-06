package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationsSection extends Section {
    private static final long serialVersionUID = 1L;
    private List<Organization> organizations;

    public OrganizationsSection() {
    }

    public OrganizationsSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations,"Time periods must not be null");
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationsSection that = (OrganizationsSection) o;

        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }

    @Override
    public String toString() {
        return "OrganizationsSection{" +
                "organizations=" + organizations +
                '}';
    }
}
