package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void save(Resume r) {
        int matchIndex = getIndex(r.getUuid());
        if (matchIndex >= 0) {
            System.out.println("This resume is already in storage");
            return;
        }
        if (size == storage.length) {
            System.out.println("Storage at full capacity");
            return;
        }
        int insertionPoint = -matchIndex - 1;
        System.arraycopy(storage,insertionPoint,storage, insertionPoint + 1, size - insertionPoint );
        storage[insertionPoint] = r;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int matchIndex = getIndex(uuid);
        if (matchIndex < 0) {
            System.out.println("No such resume in storage");
            return;
        }
        System.arraycopy(storage, matchIndex + 1, storage, matchIndex, size - matchIndex + 1);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}