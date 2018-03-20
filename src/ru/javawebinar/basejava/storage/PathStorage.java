package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path path;
    private final ResumeSerializer resumeSerializer;

    public PathStorage(String path, ResumeSerializer resumeSerializer) {
        Objects.requireNonNull(path, "Path must not be null");
        Objects.requireNonNull(resumeSerializer, "resumeSerializer must not be null");
        this.path = Paths.get(path);
        this.resumeSerializer = resumeSerializer;
        if (!Files.isDirectory(this.path) || !Files.isReadable(this.path) || !Files.isWritable(this.path)) {
            throw new IllegalArgumentException(path + " is not a readable/writable directory");
        }
    }

    @Override
    protected boolean isInStorage(Path key) {
        return Files.isRegularFile(key);
    }

    @Override
    protected Path getMatchKey(String uuid) {
        return path.resolve(uuid);
    }

    @Override
    protected void doUpdate(Path key, Resume r) {
        try {
            resumeSerializer.doWrite(r, new BufferedOutputStream(Files.newOutputStream(key)));
        } catch (IOException ioe) {
            throw new StorageException("Could not write to file", key.getFileName().toString(), ioe);
        }
    }

    @Override
    protected void doSave(Path key, Resume r) {
        try {
            Files.createFile(key);
            doUpdate(key, r);
        } catch (IOException ioe) {
            throw new StorageException("Could create resume ", key.getFileName().toString(), ioe);
        }
    }

    @Override
    protected Resume doGet(Path key) {
        try {
            return resumeSerializer.doRead(new BufferedInputStream(Files.newInputStream(key)));
        } catch (IOException ioe) {
            throw new StorageException("Could not read file ", key.getFileName().toString(), ioe);
        }
    }

    @Override
    protected void doDelete(Path key) {
        try {
            Files.delete(key);
        } catch (IOException ioe) {
            throw new StorageException("Resume delete error ", key.getFileName().toString(), ioe);
        }
    }

    @Override
    protected List<Resume> getAllAsList() {
        return listFiles().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        listFiles().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) listFiles().count();
    }

    private Stream<Path> listFiles() {
        try {
            return Files.list(this.path);
        } catch (IOException ioe) {
            throw new StorageException("Could not read directory ", null, ioe);
        }
    }
}
