package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage,null);
        size = 0;
    }

    @Override
    public Resume get(String uuid) {
        int matchIndex = getIndex(uuid);
        if(matchIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[matchIndex];
    }

    @Override
    public void update(Resume r) {
        int matchIndex = getIndex(r.getUuid());
        if(matchIndex < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        storage[matchIndex] = r;
    }

    @Override
    public void save(Resume r) {
        int matchIndex = getIndex(r.getUuid());
        if (matchIndex > 0) {
            throw new ExistStorageException(r.getUuid());
        }
        if (size == storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertResume(r,matchIndex);
        size++;
    }

    @Override
    public void delete(String uuid) {
        int matchIndex = getIndex(uuid);
        if(matchIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(matchIndex);
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume r, int matchIndex);

    protected abstract void deleteResume(int matchIndex);
}