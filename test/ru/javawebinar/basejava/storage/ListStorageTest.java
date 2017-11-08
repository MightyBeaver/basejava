package ru.javawebinar.basejava.storage;

import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;


public class ListStorageTest extends AbstractStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(testResumes[0].getUuid());
        storage.get(testResumes[0].getUuid());
    }

    @Override
    @Test
    public void saveWhenFull() throws Exception {
        //never "full"
    }
}