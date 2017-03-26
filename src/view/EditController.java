package view;

/**
 * Created by qwerty on 26-Mar-17.
 */

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Product;
import model.ProductType;
import view.ProductController;

public class EditController {
    @FXML
    private TextField name;
    @FXML
    private TextField amount;
    @FXML
    private ComboBox<ProductType> type;
    @FXML
    private CheckBox available;

    private Stage dialogStage;
    private Product product;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        type.getItems().addAll(ProductType.Drink,ProductType.Food,ProductType.Medicament);
        type.getSelectionModel().selectFirst();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Product product) {
        this.product = product;

        name.setText(product.getName());
        amount.setText(String.valueOf(product.getAmount()));
        if(product.isAvailable())
        {
            available.setSelected(true);
        }
        else
        {
            available.setSelected(false);
        }
        type.getSelectionModel().select(product.getType());
    }
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if(isInputValid()) {
            product.setName(name.getText());
            product.setAmount(Integer.parseInt(amount.getText()));
            product.setType(type.getValue());
            product.setAvailable(available.isSelected());
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (name.getText() == null || name.getText().length() == 0) {
            errorMessage += "Nie podales imienia!\n";
        }

        if (amount.getText() == null || amount.getText().length() == 0) {
            errorMessage += "Nie podales ilosci produktow!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(amount.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Bledna ilosc produktow. Podaj liczbe!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Blednie podane pola");
            alert.setHeaderText("Popraw pola");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
