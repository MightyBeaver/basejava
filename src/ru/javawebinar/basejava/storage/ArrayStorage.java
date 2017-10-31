package ru.javawebinar.basejava.storage;

import java.util.Arrays;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage,null);
        size = 0;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume r) {
        int matchIndex = getIndex(r.getUuid());
        if (matchIndex != -1) {
            System.out.println("This resume is already in storage");
            return;
        }
        if (size == storage.length) {
            System.out.println("Storage at full capacity");
            return;
        }
        storage[size] = r;
        size++;
    }

    public void update(Resume r) {
        int matchIndex = getIndex(r.getUuid());
        if(matchIndex == -1) {
            System.out.println("No such resume in storage");
            return;
        }
        storage[matchIndex] = r;
    }

    public Resume get(String uuid) {
        int matchIndex = getIndex(uuid);
        if (matchIndex == -1) {
            return null;
        }
        return storage[matchIndex];
    }

    public void delete(String uuid) {
        int matchIndex = getIndex(uuid);
        if (matchIndex == -1) {
            System.out.println("No such resume in storage");
            return;
        }
        storage[matchIndex] = storage[size -1];
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}
