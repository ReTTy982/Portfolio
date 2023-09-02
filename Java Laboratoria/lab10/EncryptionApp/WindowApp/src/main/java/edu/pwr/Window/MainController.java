package edu.pwr.Window;

import edu.pwr.encryption.AesEncrypt;
import edu.pwr.encryption.EncryptionTypeEnum;
import edu.pwr.encryption.RsaEncrypt;
import edu.pwr.key.AesKeyGenerator;
import edu.pwr.key.KeyTypeEnum;
import edu.pwr.key.RsaKeyGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class MainController {

    String inputPath = null;
    String fileName = null;
    RsaEncrypt rsaEncrypt;
    RsaKeyGenerator rsaKeyGenerator;
    AesKeyGenerator aesKeyGenerator;
    AesEncrypt aesEncrypt;
    @FXML
    private TextField inputPathTxt;
    @FXML
    private TextField outputPathKeyTxt;
    @FXML
    private TextField outputPathEncTxt;

    @FXML
    private TextField fileNameTxt;
    @FXML
    private ChoiceBox algorythmChoiceBox;
    @FXML
    private ChoiceBox keyChoiceBox;

    @FXML
    private Button inputBtn;

    @FXML
    private Button inputKeyBtn;

    @FXML
    private Button acceptKeyBtn;
    @FXML
    private Button encodeBtn;
    @FXML
    private Button decodeBtn;

    @FXML
    private Button chooseKeyBtn;
    @FXML
    private void chooseInputFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            inputPath = selectedFile.getAbsolutePath();
            if(event.getSource() == inputBtn){
                inputPathTxt.setText(inputPath);
            } else if (event.getSource() == chooseKeyBtn) {
                outputPathEncTxt.setText(inputPath);
                
            }

        }
    }

    @FXML
    private void chooseKeyOutputPath(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Wybierz folder ");

        File selectedFolder = directoryChooser.showDialog(null);

        if (selectedFolder != null) {
            String folderPath = selectedFolder.getAbsolutePath();
            outputPathKeyTxt.setText(folderPath);
        }
    }
    @FXML
    private void chooseOutputFile(){}

    @FXML
    private void encryptFile() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        String filePath = inputPathTxt.getText();
        String keyPath = outputPathEncTxt.getText();
        String type = (String) algorythmChoiceBox.getValue();
        if (type == "RSA"){
            rsaEncrypt.encrypt(keyPath,filePath);
        }
        else {
            aesEncrypt.encrypt(keyPath,filePath);
        }

    }

    @FXML
    private void decryptFile() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        String filePath = inputPathTxt.getText();
        String keyPath = outputPathEncTxt.getText();
        String type = (String) algorythmChoiceBox.getValue();

        if (type == "RSA"){
            rsaEncrypt.decrypt(filePath,keyPath);
        }
        else {
            aesEncrypt.decrypt(filePath,keyPath);
        }
    }

    @FXML
    private void generateKeys() throws NoSuchAlgorithmException, IOException {
        String outputPath = outputPathKeyTxt.getText();
        String fileName = fileNameTxt.getText();
        String type = (String) keyChoiceBox.getValue();
        if (type=="RSA"){
            rsaKeyGenerator.generate(2048,outputPath,fileName);
        } else if (type=="AES") {
            aesKeyGenerator.generate(128,outputPath,fileName);
        }
    }




    @FXML
    private void initialize(){
        rsaEncrypt = new RsaEncrypt();
        rsaKeyGenerator = new RsaKeyGenerator();
        aesKeyGenerator = new AesKeyGenerator();
        aesEncrypt = new AesEncrypt();



        String[] keyEnum = new String[KeyTypeEnum.values().length];
        String[] encryptionEnum = new String[EncryptionTypeEnum.values().length];
        for (int i = 0; i < KeyTypeEnum.values().length; i++) {
            keyEnum[i] = KeyTypeEnum.values()[i].toString();
        }

        for (int i = 0; i < EncryptionTypeEnum.values().length; i++) {
            encryptionEnum[i] = EncryptionTypeEnum.values()[i].toString();
        }

        keyChoiceBox.getItems().addAll(keyEnum);
        algorythmChoiceBox.getItems().addAll(encryptionEnum);

    }




}