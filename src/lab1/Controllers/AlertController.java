package lab1.Controllers;

import javafx.scene.control.Alert;

public class AlertController {
    public enum WarningTitles {
        Cipher,
        FileIO
    }

    public static void showErrorAlert(WarningTitles title, String message) throws Exception {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(getTitle(title));
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showWarningAlert(WarningTitles title, String message) throws Exception {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(getTitle(title));
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static String getTitle (WarningTitles title) throws Exception {
        switch (title) {
            case Cipher:
                return "Cipher exception!";
            case FileIO:
                return "Input/Output exception!";
            default:
                throw new Exception("No such WarningTitle");
        }
    }
}
