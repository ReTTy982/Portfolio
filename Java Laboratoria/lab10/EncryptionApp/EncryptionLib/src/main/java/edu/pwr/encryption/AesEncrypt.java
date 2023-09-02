package edu.pwr.encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.*;

public class AesEncrypt {

    public void encrypt(String keyPath, String filePath) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] fileBytes = readFromFile(filePath);
        SecretKeySpec secretKeySpec = new SecretKeySpec(getKey(keyPath),"AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[16]);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,ivParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(fileBytes);

        FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(String.valueOf(Paths.get(filePath).getParent()), "_EncodedAes.png").toFile());
        fileOutputStream.write(encryptedBytes);
        fileOutputStream.close();

    }

    public void decrypt(String inputFilePath, String keyPath) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] encryptedBytes = readFromFile(inputFilePath);
        SecretKeySpec secretKeySpec = new SecretKeySpec(getKey(keyPath), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[16]);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(String.valueOf(Paths.get(inputFilePath).getParent()), "_DecodedAes.png").toFile());
        fileOutputStream.write(decryptedBytes);
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
