package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;


public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test
    @Ignore
    public void saveWhenFull(){}

    @Override
    @Test
    public void getAll() throws Exception {
        Resume[] fromStorage = storage.getAll();
        Arrays.sort(fromStorage);
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(testResumes[0],fromStorage[0]);
        Assert.assertEquals(testResumes[1],fromStorage[1]);
        Assert.assertEquals(testResumes[2],fromStorage[2]);
    }
}