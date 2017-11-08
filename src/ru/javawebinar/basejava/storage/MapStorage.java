package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    Map<String,Resume> storage = new HashMap<>();

    @Override
    protected boolean isInStorage(Object key) {
        return storage.keySet().contains(key.toString());
    }

    @Override
    protected Object getMatchKey(String uuid) {
       return uuid;                             //глупо выходит
    }

    @Override
    protected void doUpdate(Object key, Resume r) {
        storage.put(key.toString(),r);
    }

    @Override
    protected void doSave(Object key, Resume r) {
        storage.put(key.toString(),r);
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get(key.toString());
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove(key.toString());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
