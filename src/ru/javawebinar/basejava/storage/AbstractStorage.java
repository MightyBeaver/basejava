package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        Object key = getMatchKey(r.getUuid());
        if (!isInStorage(key)) {
            throw new NotExistStorageException(r.getUuid());
        }
        doUpdate(key, r);
    }

    @Override
    public void save(Resume r) {
        Object key = getMatchKey(r.getUuid());
        if (isInStorage(key)) {
            throw new ExistStorageException(r.getUuid());
        }
        doSave(key, r);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getMatchKey(uuid);
        if (!isInStorage(key)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(key);
    }

    @Override
    public void delete(String uuid) {
        Object key = getMatchKey(uuid);
        if (!isInStorage(key)) {
            throw new NotExistStorageException(uuid);
        }
        doDelete(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> sortedResumes = getAllAsList();
        Collections.sort(sortedResumes);
        return  sortedResumes;
    }

    protected abstract boolean isInStorage(Object key);
    protected abstract Object getMatchKey(String uuid);
    protected abstract void doUpdate(Object key, Resume r);
    protected abstract void doSave(Object key, Resume r);
    protected abstract Resume doGet(Object key);
    protected abstract void doDelete(Object key);
    protected abstract List<Resume> getAllAsList();

}
