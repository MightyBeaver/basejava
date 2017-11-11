package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
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
    protected Resume doGet(Object key) {
        return storage[(Integer)key];
    }

    @Override
    protected void doUpdate(Object key, Resume r) {
        storage[(Integer)key] = r;
    }

    @Override
    protected void doSave(Object key, Resume r) {
        if (size == storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertResume(r,(Integer)key);
        size++;
    }

    @Override
   protected void doDelete(Object key) {
        deleteResume((Integer) key);
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    protected List<Resume> getAllAsList() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    protected boolean isInStorage(Object key) {
        return (Integer) key >= 0;
    }

    protected abstract void insertResume(Resume r, int matchIndex);

    protected abstract void deleteResume(int matchIndex);
}