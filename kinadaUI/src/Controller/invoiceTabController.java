package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class invoiceTabController implements Initializable {

    @FXML
    private TableView<invoices> invoiceTableView;
    @FXML
    private TableColumn<invoices, Integer> orderID;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> address;
    @FXML
    private TableColumn<?, ?> updateStatus;
    @FXML
    private TableColumn<?, ?> totalPrice;
    @FXML
    private TableColumn<?, ?> status;
    @FXML
    private TableColumn<invoices, ?> orderStatus;
    @FXML
    private TableColumn<invoices, ?> paymentStatus;
    @FXML
    private ImageView searchImageView2;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField searchInvoiceTF;

    public static ObservableList<invoices> invoice = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File searchFile2 = new File("kinadaImage/loupe.png");
        Image searchImage2 = new Image(searchFile2.toURI().toString());
        searchImageView2.setImage(searchImage2);

        invoice.clear();

        initColumns();
        addRowsTableView();
        searchInvoice();
    }


    public void initColumns() {
        orderID.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("cust_name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        address.setCellValueFactory(new PropertyValueFactory<>("order_address"));
        updateStatus.setCellValueFactory(new PropertyValueFactory<>("update"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        paymentStatus.setCellValueFactory(new PropertyValueFactory<>("payment_stat"));
        orderStatus.setCellValueFactory(new PropertyValueFactory<>("order_stat"));

    }

    // Add data in a form of row in a tableView one by one
    public void addRowsTableView() {

        // Searching data
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String findPrice = "SELECT o.OrderID, OrderDate, Name, Address, o.Status, p.PaymentID, TotalPrice, p.Status " +
                "FROM customer c, orders o, payment p WHERE p.OrderID = o.OrderID and o.CustomerID = c.CustomerID ORDER BY OrderDate";

        try {
            Statement stat = connectDB.createStatement();
            ResultSet rs = stat.executeQuery(findPrice);

            while (rs.next()) {
                //Integer count = rs.getInt("COUNT(*)");
                Integer orderID = rs.getInt("o.OrderID");
                Date orderDate = rs.getDate("OrderDate");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                String orderStatus = rs.getString("o.Status");
                Integer paymentID = rs.getInt("p.PaymentID");
                Integer total = rs.getInt("TotalPrice");
                String paymentStatus = rs.getString("p.Status");

                ComboBox<String> paymentComboBox = new ComboBox<>(FXCollections.observableArrayList(paymentStatus, "PAID"));
                paymentComboBox.setValue(paymentStatus);
                ComboBox<String> orderComboBox = new ComboBox<>(FXCollections.observableArrayList(orderStatus, "DELIVERED"));
                orderComboBox.setValue(orderStatus);



                // Add the data into the observableList
                invoice.add(new invoices(orderID, orderDate, name, address, paymentID, total, paymentComboBox, orderComboBox, new Button()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        invoiceTableView.setItems(getInvoice());
    }

    // Get all the data in the observable list
    public ObservableList<invoices> getInvoice() {
        return invoice;
    }


    public void searchInvoice() {
        FilteredList<invoices> filteredData = new FilteredList<>(invoice, i -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchInvoiceTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(iv -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (iv.getCust_name().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<invoices> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(invoiceTableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        invoiceTableView.setItems(sortedData);
    }

    public void toInvoiceDetail() {
        for (invoices i : invoiceTableView.getSelectionModel().getSelectedItems()) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/invoiceDetail.fxml"));
                Parent root = loader.load();

                //The following both lines are the only addition we need to pass the arguments
                invoiceDetailController controller = loader.getController();
                controller.setInvoiceDetail(i.getOrder_id(), i.getDate(), i.getCust_name(), i.getOrder_address(), i.getTotal_price());

                Stage invoiceDetail = new Stage();
                invoiceDetail.setScene(new Scene(root, 350, 400));
                invoiceDetail.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }
    public void deleteButtonOnAction(ActionEvent event) {
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String deleteOrder = " DELETE FROM orders WHERE OrderID ="+invoiceTableView.getSelectionModel().getSelectedItem().getOrder_id()+"";

        try {
            Statement stat = connectDB.createStatement();
            stat.executeUpdate(deleteOrder);
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
        invoice.clear();
        addRowsTableView();
    }
}
