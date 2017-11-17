package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    List<Resume> storage = new ArrayList<>();

    @Override
    protected boolean isInStorage(Integer key) {
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
    protected void doUpdate(Integer key, Resume r) {
        storage.set(key,r);
    }

    @Override
    protected void doSave(Integer key, Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume doGet(Integer key) {
        return storage.get(key);
    }

    @Override
    protected void doDelete(Integer key) {
        storage.remove(key.intValue());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getAllAsList() {
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
       return storage.size();
    }
}
