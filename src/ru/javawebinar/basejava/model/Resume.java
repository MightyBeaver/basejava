package ru.javawebinar.basejava.model;

import java.util.UUID;

/**
 * ru.javawebinar.basejava.model.Resume class
 */
public class Resume implements Comparable<Resume>{

    // Unique identifier
    private final String uuid;
    private final String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return 31*uuid.hashCode() + fullName.hashCode();
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