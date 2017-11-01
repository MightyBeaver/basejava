package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage,null);
        size = 0;
    }

    public Resume get(String uuid) {
        int matchIndex = getIndex(uuid);
        if (matchIndex < 0) {
            System.out.println("No such resume in storage");
            return null;
        }
        return storage[matchIndex];
    }

    public void update(Resume r) {
        int matchIndex = getIndex(r.getUuid());
        if(matchIndex < 0) {
            System.out.println("No such resume in storage");
            return;
        }
        storage[matchIndex] = r;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);
}