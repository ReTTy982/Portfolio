package edu.pwr.key;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.*;

public class AesKeyGenerator {


    public void generate(int size, String path, String fileName) throws NoSuchAlgorithmException, IOException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(size);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(path,fileName).toFile());
        fileOutputStream.write(keyBytes);
        fileOutputStream.close();
    }
}
