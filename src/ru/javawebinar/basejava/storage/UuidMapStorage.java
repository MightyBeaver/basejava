package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class UuidMapStorage extends AbstractMapStorage {

    @Override
    protected boolean isInStorage(Object uuid) {
        return storage.containsKey(uuid.toString());
    }
    @Override
    protected String getMatchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Object uuid, Resume r) {
        storage.put(uuid.toString(),r);
    }

    @Override
    protected void doSave(Object uuid, Resume r) {
        storage.put(uuid.toString(),r);
    }

    @Override
    protected Resume doGet(Object uuid) {
        return storage.get(uuid.toString());
    }

    @Override
    protected void doDelete(Object uuid) {
        storage.remove(uuid.toString());
    }
}
