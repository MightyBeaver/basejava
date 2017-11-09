package ru.javawebinar.basejava.storage;

import java.util.Arrays;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getMatchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(Resume r, int matchIndex){
        storage[size] = r;
    }

    @Override
    public void deleteResume(int matchIndex) {
        storage[matchIndex] = storage[size -1];
    }
}
