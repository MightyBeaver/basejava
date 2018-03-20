package ru.javawebinar.basejava.storage;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamResumeSerializer()));
    }
}