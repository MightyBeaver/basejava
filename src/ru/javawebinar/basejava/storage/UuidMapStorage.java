package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class UuidMapStorage extends AbstractMapStorage<String> {

    @Override
    protected boolean isInStorage(String uuid) {
        return storage.containsKey(uuid);
    }
    @Override
    protected String getMatchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(String uuid, Resume r) {
        storage.put(uuid,r);
    }

    @Override
    protected void doSave(String uuid, Resume r) {
        storage.put(uuid,r);
    }

    @Override
    protected Resume doGet(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doDelete(String uuid) {
        storage.remove(uuid);
    }
}
