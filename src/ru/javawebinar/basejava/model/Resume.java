package ru.javawebinar.basejava.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * ru.javawebinar.basejava.model.Resume class
 */
public class Resume implements Comparable<Resume>{

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private final Map<Contacts,Link> contacts = new EnumMap<>(Contacts.class);
    private final Map<SectionType,Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Link getContactLink (Contacts contact) {
        return  contacts.get(contact);
    }

    public Section getSection(SectionType sectionType) {
        return  sections.get(sectionType);
    }

    public void addContact(Contacts contact, String value, String url) {
        Objects.requireNonNull(contact,"Contact type must not be null");
        contacts.put(contact,new Link(value,url));
    }

    public void addSection(SectionType sectionType, Section section) {
        Objects.requireNonNull(sectionType,"Section type must not be null");
        Objects.requireNonNull(section,"Section must not be null");
        sections.put(sectionType,section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        if (!fullName.equals(resume.fullName)) return false;
        if (contacts != null ? !contacts.equals(resume.contacts) : resume.contacts != null) return false;
        return sections != null ? sections.equals(resume.sections) : resume.sections == null;
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (sections != null ? sections.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "uuid: "+ uuid + " Full name: "+fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int nameCompare = fullName.compareTo(o.fullName);
        if (nameCompare != 0) {
            return nameCompare;
        } else {
            return  uuid.compareTo(o.uuid);
        }
    }
}