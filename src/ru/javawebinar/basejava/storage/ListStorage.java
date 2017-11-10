package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> storage = new ArrayList<>();

    @Override
    protected boolean isInStorage(Object key) {
        return key != null;
    }

    @Override
    protected Integer getMatchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Object key, Resume r) {
        storage.set((Integer)key,r);
    }

    @Override
    protected void doSave(Object key, Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove(((Integer)key).intValue());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllAsList() {
        return storage;
    }

    @Override
    public int size() {
       return storage.size();
    }
}
