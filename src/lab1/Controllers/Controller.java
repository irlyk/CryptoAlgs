package lab1.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lab1.Ciphers.GammingСipher;
import lab1.Ciphers.Permutation;
import lab1.Objects.ByteText;
import lab1.Objects.ReaderByte;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    public enum ciphers {
        Gamming,
        Permutation,
        Substitution,
        XOR,
        OnetimePad
    }

    ObservableList<String> ciphers = FXCollections.observableArrayList(
            "Gamming",
            "Permutation",
            "substitution",
            "XOR",
            "One-time pad"
    );

    private ByteText read_text;
    private ByteText encrypted_text;
    private ByteText decrypted_text;

    private String EncPath;
    private String DecPath;
    private String SourcePtah;

    @FXML private ResourceBundle resources;

    @FXML private URL location;

    @FXML private Button btnWriteEncrypt;

    @FXML private Button btnWriteDecrypt;

    @FXML private TextField tfInputFileName;

    @FXML private Button btnEncrypt;

    @FXML private TextArea taReadText;

    @FXML private Label lblCipherChoise;

    @FXML private TextField tfOutputFileName;

    @FXML private ComboBox<String> cbCipher;

    @FXML private Label lblOutputFile;

    @FXML private Label lblInputFile;

    @FXML private TextArea taEncryptText;

    @FXML private Button btnDecrypt;

    @FXML private Button btnReadText;

    @FXML private TextField tfKey;

    @FXML
    void initialize() {
        EncPath = "src\\lab1\\Files\\Output\\Encrypted\\";
        DecPath = "src\\lab1\\Files\\Output\\Decrypted\\";
        SourcePtah = "src\\lab1\\Files\\Input\\";
        cbCipher.setItems(ciphers);
        cbCipher.setValue(ciphers.get(0));
        cbCipher.setOnAction(e -> comboBox_choise());
    }

    private void comboBox_choise(){
        String cipher = cbCipher.getValue();
        switch (cipher){
            case "One-time pad":
                tfKey.setEditable(false);
                break;
            default:
                tfKey.setEditable(true);
        }
    }

    private byte[] encrypt_with_chosen_cipher(boolean flag){
        String cipher = cbCipher.getValue();
        System.out.println("Chosen cipher: " + cbCipher.getValue());
        String key = tfKey.getText();
        if (key.equals("")){ return null; }
        byte[] result = null;
        byte[] text;
        if (flag) text = read_text.getByte_text();
        else text = encrypted_text.getByte_text();
        switch (cipher){
            case "Gamming":
                if (flag) result = GammingСipher._gamma_encrypt(key, text);
                else result = GammingСipher._gamma_decrypt(key, text);
                break;
            case "Permutation":
                if (flag) result = Permutation._permutation_encrypt(key, text);
                else result = Permutation._permutation_decrypt(key, text);
                break;
            case "substitution":
                break;
            case "XOR":
                result = GammingСipher.xor(key, text);
                break;
            case "One-time pad":
                break;
        }
        return result;
    }


    public void actionButtonPressed(ActionEvent actionEvent) throws Exception {

        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;
        boolean research = false;

        switch (clickedButton.getId()) {
            case "btnReadText":
                if (tfInputFileName.getText().equals("")) {
                    AlertController.showWarningAlert(AlertController.WarningTitles.FileIO, "Не распознано поле для ввода");
                    return;
                }
                String absolute_path = SourcePtah + tfInputFileName.getText();
                read_text = new ByteText();
                ReaderByte br = new ReaderByte();
                read_text.setByte_text(br.readFile_byte(absolute_path));

                if (read_text.getByte_text() == null) {
                    AlertController.showWarningAlert(AlertController.WarningTitles.FileIO, "Не удалось прочиать файл: " + SourcePtah + tfInputFileName.getText());
                    return;
                }
                System.out.println("Считан файл: " + absolute_path);
                if (read_text.toString().length() > 300) {
                    taReadText.setText(read_text.toString().substring(0, 300));
                } else {
                    taReadText.setText(read_text.toString());
                }
                break;

            case "btnEncrypt":
                if (read_text.getByte_text() == null) {
                    AlertController.showWarningAlert(AlertController.WarningTitles.Cipher, "Нельзя зашифровать пустой текс");
                    System.out.println("read_text пустое");
                    return;
                }
                encrypted_text = new ByteText();
                byte[] encryption = encrypt_with_chosen_cipher(true);
                if (encryption == null) {
                    AlertController.showWarningAlert(AlertController.WarningTitles.Cipher, "Шифровка вернула пустой текст");
                    System.out.println("EN: Массив = NULL");
                    return;
                }
                encrypted_text.setByte_text(encryption);
                System.out.println("Текст зашифрован");
                if (encrypted_text.toString().length() > 300) {

                    taEncryptText.setText(encrypted_text.toString().substring(0, 300));
                } else {
                    taEncryptText.setText(encrypted_text.toString());
                }
                break;

            case "btnDecrypt":
                if (encrypted_text != null && encrypted_text.getByte_text() != null) {
                    decrypted_text = new ByteText();
                    byte[] decrypt = encrypt_with_chosen_cipher(false);
                    if (decrypt!= null){
                        decrypted_text.setByte_text(decrypt);
                        System.out.println("Текст расшифрован");
                        if (decrypted_text.toString().length() > 300) {
                            taEncryptText.setText(decrypted_text.toString().substring(0, 300));
                        } else {
                            taEncryptText.setText(decrypted_text.toString());
                        }
                    } else {
                        System.out.println("DE: Массив = NULL");
                    }
                } else {
                    System.out.println("TaEnc пустое");
                }
                break;

            case "btnWriteEncrypt":
                if(encrypted_text.getByte_text() != null){
                    if (!tfOutputFileName.getText().equals("")){
                        if ( encrypted_text != null && encrypted_text.getByte_text() != null) {
                            ReaderByte rb = new ReaderByte();
                            rb.writeFile_byte(EncPath + tfOutputFileName.getText(), encrypted_text.getByte_text());
                            System.out.println("Encrypted text write in: " + tfOutputFileName.getText());
                        } else {
                            System.out.println("Decrypted is null");
                        }
                    } else {
                        System.out.println("No output filename");
                    }
                } else {
                    System.out.println("Encrypt_text is empty");
                }
                break;

            case "btnWriteDecrypt":
                if(decrypted_text.getByte_text() != null){
                    if (!tfOutputFileName.getText().equals("")){
                        if (decrypted_text != null && decrypted_text.getByte_text() != null) {
                            ReaderByte rb = new ReaderByte();
                            rb.writeFile_byte(DecPath + tfOutputFileName.getText(), decrypted_text.getByte_text());
                            System.out.println("Decrypted text write in: " + tfOutputFileName.getText());
                        } else {
                            System.out.println("Decrypted is null");
                        }
                    } else {
                        System.out.println("No output filename");
                    }
                } else {
                    System.out.println("Decrypt_text is empty");
                }
                break;
        }
    }
}
