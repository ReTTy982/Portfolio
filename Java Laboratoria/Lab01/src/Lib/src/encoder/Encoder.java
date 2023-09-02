package encoder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Encoder {
    private HashMap<Path, String> newMap = new HashMap<Path, String>();
    private HashMap<Path, String> oldMap = new HashMap<Path, String>();
    private HashMap<String,List<Path>> diff = new HashMap<String,List<Path>>();


    private static DirectoryStream<Path> getStream(Path path) throws IOException {
        DirectoryStream<Path> paths = Files.newDirectoryStream(path);
        return paths;
    }

    public HashMap<String,List<Path>> runEncoder(Path filePath, String snapName) throws IOException, NoSuchAlgorithmException {
        String dir = "\\.snap";
        String userHomeDir = System.getProperty("user.home");
        Path path = Paths.get(userHomeDir + dir);

        Path snap = path.resolve(snapName + ".txt");


        if (!Files.isDirectory(path)) {
            Files.createDirectory(path);
        }
        if (Files.exists(snap) == false) {
            Files.createFile(snap);
        }
        BufferedReader br = new BufferedReader(new FileReader(snap.toFile()));
        String line = null;

        while ((line = br.readLine()) != null) {
            String[] splited = line.split("&&");
            Path oldPath = Paths.get(splited[0].trim());
            String oldSum = splited[1].trim();
            oldMap.put(oldPath, oldSum);

        }

        listFiles(filePath, newMap);
        getDiff();


        BufferedWriter bw = new BufferedWriter(new FileWriter(snap.toFile()));


        for (Path i : newMap.keySet()) {
            bw.write(i + "&&" + newMap.get(i));
            bw.newLine();
        }

        bw.flush();
        return diff;
    }

    private static void listFiles(Path path, HashMap<Path, String> map) throws IOException, NoSuchAlgorithmException {
        DirectoryStream<Path> paths = getStream(path);

        for (Path p : paths) {
            if (p.toFile().isDirectory()) {
                listFiles(p, map);
            } else {
                byte[] data = Files.readAllBytes(p);
                byte[] hash = MessageDigest.getInstance("MD5").digest(data);
                String checksum = new BigInteger(1, hash).toString(16);
                map.put(p, checksum);
            }
        }
    }

    private void getDiff() {
        List<Path> added = new ArrayList<Path>();
        List<Path> removed = new ArrayList<Path>();
        List<Path> changed = new ArrayList<Path>();

        for (Path p : newMap.keySet()) {
            if (oldMap.containsKey(p)) {
                if (!oldMap.get(p).equals(newMap.get(p))) {
                    changed.add(p);
                }
                oldMap.remove(p);
            }
            else{
                added.add(p);
            }
        }
        for(Path p : oldMap.keySet()){
            removed.add(p);
        }
        diff.put("ADDED:",added);
        diff.put("REMOVED:",removed);
        diff.put("CHANGED:",changed);
    }
}

