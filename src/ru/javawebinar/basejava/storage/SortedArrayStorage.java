package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage{

    private static final Comparator<Resume> UUID_COMPARATOR = Comparator.comparing(Resume::getUuid);
    @Override
    protected void insertResume(Resume r,int matchIndex) {
        int insertionPoint = -matchIndex - 1;
        System.arraycopy(storage,insertionPoint,storage, insertionPoint + 1, size - insertionPoint );
        storage[insertionPoint] = r;
    }

    @Override
    public void deleteResume(int matchIndex) {
        System.arraycopy(storage, matchIndex + 1, storage, matchIndex, size - matchIndex + 1);
    }

    @Override
    protected Integer getMatchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size,searchKey, UUID_COMPARATOR);
    }
}