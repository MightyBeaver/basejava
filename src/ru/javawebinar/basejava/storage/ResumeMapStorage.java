package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ResumeMapStorage extends AbstractMapStorage<Resume> {

    @Override
    protected boolean isInStorage(Resume resume) {
        return resume!= null;
    }

    @Override
    protected Resume getMatchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Resume r) {
        storage.put(r.getUuid(),r);
    }

    @Override
    protected void doSave(Resume resume, Resume r) {
        storage.put(r.getUuid(),r);
    }

    @Override
    protected Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    protected void doDelete(Resume resume) {
        storage.remove(resume.getUuid());
    }

}
