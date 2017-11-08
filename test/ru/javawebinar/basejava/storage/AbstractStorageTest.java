package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;


public abstract class AbstractStorageTest {

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    protected static final Resume[] testResumes = new Resume[]
            {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3), new Resume(UUID_4)};

   public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(testResumes[0]);
        storage.save(testResumes[1]);
        storage.save(testResumes[2]);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        storage.update(testResumes[0]);
        Assert.assertTrue(testResumes[0] == storage.get(testResumes[0].getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNonExistent() throws Exception {
        storage.update(testResumes[3]);
    }

    @Test
    public void save() throws Exception {
        storage.save(testResumes[3]);
        Assert.assertEquals(4,storage.size());
        Assert.assertTrue(testResumes[3] == storage.get(testResumes[3].getUuid()));
    }

    @Test(expected = StorageException.class)
    public void saveWhenFull() throws Exception {
       try {
           for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
               storage.save(new Resume());
           }
       } catch (StorageException e) {
           Assert.fail();
       }
        storage.save(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistent() throws Exception {
        storage.save(testResumes[0]);
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(testResumes[0].getUuid());
        Assert.assertEquals(2,storage.size());
        storage.get(testResumes[0].getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNonExistent() throws Exception {
        storage.delete("asdf");
    }

    @Test
    public void get() throws Exception {
        Assert.assertTrue(testResumes[2] == storage.get(testResumes[2].getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistent() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAll() throws Exception {
        Resume[] fromStorage = storage.getAll();
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(testResumes[0],fromStorage[0]);
        Assert.assertEquals(testResumes[1],fromStorage[1]);
        Assert.assertEquals(testResumes[2],fromStorage[2]);
    }


}