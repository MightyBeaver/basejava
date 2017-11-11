package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ResumeMapStorage extends AbstractMapStorage {

    @Override
    protected boolean isInStorage(Object resume) {
        return resume!= null;
    }

    @Override
    protected Resume getMatchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doUpdate(Object resume, Resume r) {
        storage.put(r.getUuid(),r);
    }

    @Override
    protected void doSave(Object resume, Resume r) {
        storage.put(r.getUuid(),r);
    }

    @Override
    protected Resume doGet(Object resume) {
        return (Resume)resume;
    }

    @Override
    protected void doDelete(Object resume) {
        storage.remove(((Resume)resume).getUuid());
    }

}
