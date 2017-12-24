package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * gkislin
 * 22.07.2016
 */
public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Error when reading storage directory",null);
        }
            for(File file: files){
                if(file.isFile())
                    doDelete(file);
            }
    }

    @Override
    public int size() {
        String[] names = directory.list();
        if (names == null) {
            throw new StorageException("Error when reading storage directory",null);
        }
        return names.length;
    }

    @Override
    protected File getMatchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException ioe) {
            throw new StorageException("File write error", r.getUuid(), ioe);
        }
    }

    @Override
    protected boolean isInStorage(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(File file, Resume r) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Error when creating new file ", file.getName(), e);
        }
        doUpdate(file,r);
    }
    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException ioe) {
            throw new StorageException("Error when reading file ",file.getName(),ioe);
        }
    }

    @Override
    protected void doDelete(File file) {
        if(!file.delete()) {
            throw new StorageException("File " + file.getName()+ " could not be deleted",file.getName());
        }
    }

    @Override
    protected List<Resume> getAllAsList() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Error when reading storage directory", null);
        }
        List<Resume> list = new ArrayList<>();
        for (File file : files) {
            list.add(doGet(file));
        }
        return list;
    }

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;
    protected abstract Resume doRead(InputStream is) throws IOException;

}
