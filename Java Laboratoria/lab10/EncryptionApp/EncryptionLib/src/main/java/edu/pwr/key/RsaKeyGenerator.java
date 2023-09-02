package edu.pwr.key;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.*;

public class RsaKeyGenerator {
    public void generate(int size, String path, String fileName) throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(size);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey =keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        FileOutputStream privateOutput = new FileOutputStream(Paths.get(path,fileName).toFile());
        privateOutput.write(privateKey.getEncoded());
        privateOutput.close();

        FileOutputStream publicOutput = new FileOutputStream(Paths.get(path,fileName+".pub").toFile());
        publicOutput.write(publicKey.getEncoded());
        publicOutput.close();
    }
}
