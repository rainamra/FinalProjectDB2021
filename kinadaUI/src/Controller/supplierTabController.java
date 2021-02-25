package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class supplierTabController implements Initializable {

    @FXML
    private TableView<suppliers> supplierTableView;

    @FXML
    private TableColumn<?, ?> supplierName;

    @FXML
    private TableColumn<?, ?> phoneNum;

    @FXML
    private TableColumn<?, ?> address;

    @FXML
    private TextField search_supplier;

    @FXML
    private Button supplier_button;

    @FXML
    private ImageView searchImageView;

    private ObservableList<suppliers> supplier = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File searchFile = new File("kinadaImage/loupe.png");
        Image searchImage = new Image(searchFile.toURI().toString());
        searchImageView.setImage(searchImage);

        initRows();
        addRowsTableView();

        supplierTableView.setItems(getSupplier());
    }

    public void initRows() {
        supplierName.setCellValueFactory(new PropertyValueFactory<>("supp_name"));
        phoneNum.setCellValueFactory(new PropertyValueFactory<>("phone_num"));
        address.setCellValueFactory(new PropertyValueFactory<>("supp_address"));
    }

    // Add data in a form of row in a tableView one by one
    public void addRowsTableView() {

        // Searching data
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String findPrice = "SELECT SupplierID, Name, PhoneNumber, Address FROM Supplier";

        try {
            Statement stat = connectDB.createStatement();
            ResultSet rs = stat.executeQuery(findPrice);

            while (rs.next()) {
                Integer supplierID = rs.getInt("SupplierID");
                String name = rs.getString("Name");
                String phoneNumber = rs.getString("PhoneNumber");
                String address = rs.getString("Address");

                // Add the data into the observableList
                supplier.add(new suppliers(supplierID, name, phoneNumber, address));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // Get all the data in the observable list
    public ObservableList<suppliers> getSupplier() {
        return supplier;
    }

    public void toIngredientDetail() {
        for (suppliers s : supplierTableView.getSelectionModel().getSelectedItems()) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/ingredientPage.fxml"));
                Parent root = loader.load();

                //The following both lines are the only addition we need to pass the arguments
                ingredientsController controller = loader.getController();
                controller.setSupplierDetail(s.getSupplierID(), s.getSupp_name(), s.getSupp_address(), s.getPhone_num());

                Stage supplierDetail = new Stage();
                supplierDetail.setScene(new Scene(root, 350, 400));
                supplierDetail.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }

}
