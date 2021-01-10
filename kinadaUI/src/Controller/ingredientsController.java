package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ingredientsController implements Initializable {

    @FXML
    private TableView<ingredients> ingredientsTableView;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> price;
    @FXML
    private Text suppID;
    @FXML
    private Text suppName;
    @FXML
    private Text suppPhoneNum;
    @FXML
    private Text suppAddress;

    private ObservableList<ingredients> ingredient = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initColumns();
        ingredientsTableView.setItems(getIngredient());
    }

    public void initColumns() {
        id.setCellValueFactory(new PropertyValueFactory<>("ingredientID"));
        name.setCellValueFactory(new PropertyValueFactory<>("ingredientName"));
        price.setCellValueFactory(new PropertyValueFactory<>("ingredientPrice"));
    }

    public ObservableList<ingredients> getIngredient() {
        return ingredient;
    }

    public void setSupplierDetail(Integer id, String name, String address, String phone){
        suppID.setText(String.valueOf(id));
        suppName.setText(name);
        suppAddress.setText(address);
        suppPhoneNum.setText(phone);

        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        Integer supplierID = Integer.valueOf(suppID.getText());

        String searchData = "SELECT g.IngredientID, g.Name, g.Price FROM ingredient g, supplier s " +
                "WHERE s.SupplierID = g.SupplierID and s.SupplierID= '"+supplierID+"'";

        try {
            Statement stat = connectDB.createStatement();
            ResultSet rs = stat.executeQuery(searchData);

            while (rs.next()) {
                Integer ingredientID = rs.getInt("g.IngredientID");
                String ingredientName = rs.getString("g.Name");
                Integer ingredientPrice = rs.getInt("g.Price");

                ingredient.add(new ingredients(ingredientID,ingredientName,ingredientPrice));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
