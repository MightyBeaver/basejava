package ru.javawebinar.basejava.storage;

import org.junit.Test;
import org.junit.Ignore;


public class ListStorageTest extends AbstractStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Test
    @Ignore
    public void saveWhenFull(){}
}