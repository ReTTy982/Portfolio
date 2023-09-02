package test;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.WeakHashMap;

public class FileBrowser {
    private WeakHashMap<Path, File > filesWeakHashMap = new WeakHashMap<>();

    public WeakHashMap<Path, File> getFilesWeakHashMap() {
        return filesWeakHashMap;
    }

    public DefaultMutableTreeNode addNodes(Path path) throws IOException {
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(new FileNode(path.toFile()));
        DirectoryStream<Path> paths = getStream(path);


        for(Path p: paths){
            if(p.toFile().isDirectory()){
                //DefaultMutableTreeNode x = new DefaultMutableTreeNode(new FileNode(p.toFile()));
                curDir.add(addNodes(p));
            }
            else{
                curDir.add(new DefaultMutableTreeNode(new FileNode(p.toFile())));
            }

        }
        return curDir;
    }

    private static DirectoryStream<Path> getStream(Path path) throws IOException {
        try{
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            return paths;
        }
        catch (AccessDeniedException e){
            return null;
        }

    }
//    public class FileNode {
//
//        private File file;
//
//        public FileNode(File file) {
//            this.file = file;
//        }
//
//        @Override
//        public String toString() {
//            String name = file.getName();
//            if (name.equals("")) {
//                return file.getAbsolutePath();
//            } else {
//                return name;
//            }
//        }
//
//        public File getFile() {
//            return file;
//        }
//    }



}
