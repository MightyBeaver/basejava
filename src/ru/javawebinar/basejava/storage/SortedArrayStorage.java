package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected void insertResume(Resume r,int matchIndex) {
        int insertionPoint = -matchIndex - 1;
        System.arraycopy(storage,insertionPoint,storage, insertionPoint + 1, size - insertionPoint );
        storage[insertionPoint] = r;
    }

    @Override
    public void deleteResume(int matchIndex) {
        System.arraycopy(storage, matchIndex + 1, storage, matchIndex, size - matchIndex + 1);
        storage[size - 1] = null;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}