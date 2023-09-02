package edu.pwr.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaEncrypt {



    public void encrypt(String publicKeyPath, String filePath) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        byte[] fileBytes = readFromFile(filePath);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(getKey(publicKeyPath)));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        int keySize = rsaPublicKey.getModulus().bitLength() / 8;


        byte[] byteBlocks = new byte[keySize];
        int outputSize = fileBytes.length % keySize;
        byte[] nextBlock = new byte[outputSize];
        FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(String.valueOf(Paths.get(filePath).getParent()), "_EncodedRsa.png").toFile());

        int blockSize = 245;

        for (int i = 0; i < fileBytes.length; i += blockSize) {
            int remainingBytes = fileBytes.length - i;
            int currentBlockSize = Math.min(blockSize, remainingBytes);
            byte[] currentBlock = new byte[currentBlockSize];

            System.arraycopy(fileBytes, i, currentBlock, 0, currentBlockSize);

            byte[] encryptedData = cipher.doFinal(currentBlock);
            fileOutputStream.write(encryptedData);
        }



    }

    public void decrypt(String inputFilePath, String privateKeyPath) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        byte[] encryptedData = readFromFile(inputFilePath);
        System.out.println(inputFilePath);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(getKey(privateKeyPath)));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        int keySize = ((RSAPrivateKey) privateKey).getModulus().bitLength() / 8;

        byte[] byteBlocks = new byte[keySize];
        FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(String.valueOf(Paths.get(inputFilePath).getParent()), "_DecodedRsa.png").toFile());

        for (int i = 0; i < encryptedData.length; i += keySize) {
            int remainingBytes = encryptedData.length - i;
            int currentBlockSize = Math.min(keySize, remainingBytes);
            byte[] currentBlock = new byte[currentBlockSize];

            System.arraycopy(encryptedData, i, currentBlock, 0, currentBlockSize);

            byte[] decryptedData = cipher.doFinal(currentBlock);
            fileOutputStream.write(decryptedData);
        }
        fileOutputStream.close();
    }


    public byte[] getKey(String path) throws IOException {
        FileInputStream keyInput = new FileInputStream(path);
        byte[] fileKeyBytes = new byte[keyInput.available()];
        keyInput.read(fileKeyBytes);
        keyInput.close();
        return fileKeyBytes;
    }

    private byte[] readFromFile(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        byte[] fileBytes = new byte[fileInputStream.available()];
        fileInputStream.read(fileBytes);
        fileInputStream.close();
        return fileBytes;
    }
}
