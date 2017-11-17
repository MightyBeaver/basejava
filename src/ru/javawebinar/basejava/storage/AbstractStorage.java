package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void update(Resume r) {
        LOG.info("Update " + r);
        SK key = getMatchKey(r.getUuid());
        if (!isInStorage(key)) {
            throw new NotExistStorageException(r.getUuid());
        }
        doUpdate(key, r);
    }

    @Override
    public void save(Resume r) {
        LOG.info("Save " + r);
        SK key = getMatchKey(r.getUuid());
        if (isInStorage(key)) {
            LOG.warning("Resume " + r.getUuid() + "already exists");
            throw new ExistStorageException(r.getUuid());
        }
        doSave(key, r);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK key = getMatchKey(uuid);
        if (!isInStorage(key)) {
            LOG.warning("Resume " + uuid + "does not exist");
            throw new NotExistStorageException(uuid);
        }
        return doGet(key);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK key = getMatchKey(uuid);
        if (!isInStorage(key)) {
            LOG.warning("Resume " + uuid + "does not exist");
            throw new NotExistStorageException(uuid);
        }
        doDelete(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("Get all sorted ");
        List<Resume> sortedResumes = getAllAsList();
        Collections.sort(sortedResumes);
        return  sortedResumes;
    }

    protected abstract boolean isInStorage(SK key);
    protected abstract SK getMatchKey(String uuid);
    protected abstract void doUpdate(SK key, Resume r);
    protected abstract void doSave(SK key, Resume r);
    protected abstract Resume doGet(SK key);
    protected abstract void doDelete(SK key);
    protected abstract List<Resume> getAllAsList();

}
