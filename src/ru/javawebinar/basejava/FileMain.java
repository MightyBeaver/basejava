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
            //fm.listFiles(".");                            //~223 ms
            //fm.listFilesStream(".");                   //~252 ms
            fm.listFilesWithVisitor(Paths.get("."));   //~154 ms
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private void listFiles(String dir) throws IOException {
        File[] currentDirFiles = new File(dir).listFiles();
        if (currentDirFiles == null) {
            throw new IOException("Error when reading directory");
        }
        for(File file: currentDirFiles){
            if(file.isFile()) {
                System.out.println(file.getCanonicalPath());
            }
            if(file.isDirectory()){
                listFiles(file.getCanonicalPath());
            }
        }
    }

    private void listFilesWithVisitor(Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path visitedFile,BasicFileAttributes fileAttributes)
                    throws IOException {
                System.out.println(visitedFile.toFile().getCanonicalPath());
                return FileVisitResult.CONTINUE;
            }
        });

    }
    private void listFilesStream(String path) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths
                    .filter(Files::isRegularFile)
                    .map(this::convertToCanonicalPath)
                    .forEach(System.out::println);
        }
    }

    private String convertToCanonicalPath(Path path){
        try {
            return path.toFile().getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}