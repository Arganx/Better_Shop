package view;

/**
 * Created by qwerty on 26-Mar-17.
 */
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MainApp;
import model.Product;

public class ProductController {
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> first;
    @FXML
    private TableColumn<Product, String> second;

    @FXML
    private Label namelable;
    @FXML
    private Label typelable;
    @FXML
    private Label amountlable;
    @FXML
    private Label availabilitylable;

    private MainApp mainApp;

    public ProductController() {
    }



    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        first.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        second.setCellValueFactory(new PropertyValueFactory<Product, String>("amount"));

        // Clear person details.
        showProductDetails(null);
        productTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProductDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        productTable.setItems(mainApp.getProductData());
    }

    private void showProductDetails(Product product) {
        if (product != null) {
            // Fill the labels with info from the person object.
            namelable.setText(product.getName());
            amountlable.setText(String.valueOf(product.getAmount()));
            typelable.setText(String.valueOf(product.getType()));
            if(product.isAvailable())
            {
                availabilitylable.setText("Dostepny");
            }
            else
            {
                availabilitylable.setText("Niedostepny");
            }
        } else {
            // Person is null, remove all the text.
            namelable.setText("");
            amountlable.setText("");
            typelable.setText("");
            availabilitylable.setText("");
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            productTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Nic nie jest wybrane");
            alert.setHeaderText("Nie wybrano produktu");
            alert.setContentText("Wybierz produkt do usuniecia i ponow probe");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewProduct() {
        Product tempProduct = new Product();
        boolean okClicked = mainApp.showPersonEditDialog(tempProduct);
        if (okClicked) {
            mainApp.getProductData().add(tempProduct);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Product selectedPerson = productTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showProductDetails(selectedPerson);
                productTable.refresh();
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
