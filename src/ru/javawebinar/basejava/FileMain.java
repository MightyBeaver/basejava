package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class FileMain {
    //private final String projectDirectory = "C:\\projects\\basejava";
    public static void main(String[] args) {
        FileMain fm = new FileMain();
        try {
            fm.listDirectories(".", "");
            //fm.listFilesStream(".");
            //fm.listFilesWithVisitor(Paths.get("."));
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private void listDirectories(String dir, String offset) throws IOException {
        File[] currentDirFiles = new File(dir).listFiles();
        if (currentDirFiles == null) {
            throw new IOException("Error when reading directory");
        }
        for(File file: currentDirFiles){
            if(file.isDirectory()){
                System.out.println(offset + file.getName());
                listDirectories(file.getCanonicalPath(), offset + "\t");
            }
        }
    }

    private void listFilesWithVisitor(Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path visitedFile,BasicFileAttributes fileAttributes)
                    throws IOException {
                System.out.println(visitedFile.getFileName());
                return FileVisitResult.CONTINUE;
            }
        });

    }
    private void listFilesStream(String path) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .forEach(System.out::println);
        }
    }
}