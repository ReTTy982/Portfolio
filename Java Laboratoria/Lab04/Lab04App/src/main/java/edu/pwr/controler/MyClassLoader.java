package edu.pwr.controler;

import java.io.File;
import java.io.IOException;
import java.lang.ClassLoader;
import java.nio.file.*;
import java.io.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


public class MyClassLoader extends ClassLoader{

    private final Path classPath;
    public MyClassLoader(Path classPath){
        this.classPath = classPath;

    }
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            byte [] classBytes = getClassData(name);
            return defineClass(name, classBytes,0,classBytes.length);

        }
        catch (IOException e){
            throw new ClassNotFoundException("Class" + name + "not found",e );
        }

    }


private byte[] getClassData(String className) throws IOException {
    Path path = classPath.resolve(className.replace(".",FileSystems.getDefault().getSeparator() ) + ".class");
    try (InputStream inputStream = Files.   newInputStream(path)) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int bufferSize = 4096;
        byte[] buffer = new byte[bufferSize];
        int bytesNumRead;
        while ((bytesNumRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesNumRead);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
public List<String> loadAllClassesLocations() throws IOException {
        List<String> classList = new ArrayList<>();
        Files.walkFileTree(this.classPath,new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes atr) throws IOException{
                String path = classPath.relativize(file).toString();
                path = path.replace(".class","");
                path = path.replace(FileSystems.getDefault().getSeparator(),".");
                classList.add(path);
                return FileVisitResult.CONTINUE;
            }

        });
        return classList;



    }







}
