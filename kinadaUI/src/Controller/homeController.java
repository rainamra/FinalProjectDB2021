package Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

import java.sql.*;
import java.util.ResourceBundle;

public class homeController implements Initializable {

    @FXML
    private ImageView staff_avatar;
    @FXML
    private Label staff_id;
    @FXML
    private Label staff_name;
    @FXML
    private Label staff_email;
    @FXML
    private TextField cust_phone;
    @FXML
    private TextField cust_address;
    @FXML
    private TextField cust_name;
    @FXML
    private ComboBox<String> cust_order;
    @FXML
    private ComboBox<String> paymentCtgry;
    @FXML
    private Spinner<Integer> item_qty;
    @FXML
    private Button confirm_button;
    @FXML
    private Button add_button;
    @FXML
    private Button delete_button;
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem logOut_button;
    @FXML
    private MenuItem profile_button;
    @FXML
    private TableView<orderDetails> addOrderTable;
    @FXML
    private TableColumn<?, ?> item_name;
    @FXML
    private TableColumn<?, ?> item_price;
    @FXML
    private TableColumn<?, ?> itemQty;
    @FXML
    private TableColumn<?, ?> subtotal;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private Label orderMessageLabel;
    
    private ObservableList<orderDetails> orderDetail = FXCollections.observableArrayList();

    private ObservableList<orders> order = FXCollections.observableArrayList();

    private ObservableList<staff> staff_data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File avatarFile = new File("kinadaImage/staff.png");
        Image avatarImage = new Image(avatarFile.toURI().toString());
        staff_avatar.setImage(avatarImage);

        initSpinner();
        showMenu();
        setPaymentCtgry();
        initColumns();


    }

    public void setStaffID(String Staff){
        staff_id.setText(Staff);

        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        Integer id = Integer.valueOf(staff_id.getText());

        String searchData = "SELECT * FROM staff WHERE StaffID='" +id+ "'";
        try {
            Statement stat = connectDB.createStatement();
            ResultSet rs = stat.executeQuery(searchData);

            while (rs.next()) {
                String firstName = rs.getString("Firstname");
                String lastName = rs.getString("Lastname");
                String Email = rs.getString("Email");
                String Address = rs.getString("Address");
                String Phone = rs.getString("PhoneNumber");

                staff_name.setText(firstName+" "+lastName);
                staff_email.setText(Email);

                staff_data.add(new staff(id,firstName,lastName, Address, Phone, Email));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // Add Order Button
    public void addButtonOnAction(ActionEvent event) {
        String name = cust_name.getText();
        String phoneNum = cust_phone.getText();
        String address = cust_address.getText();
        String item = cust_order.getValue();
        Integer qty = item_qty.getValue();

        if (!name.isBlank() && !phoneNum.isBlank() && !address.isBlank()
                && !item.isBlank() && !qty.toString().isBlank()) {
            addRowsTableView();
            // Set and show the data into the tableView
            addOrderTable.setItems(getOrderDetail());
            totalPriceLabel.setText(String.valueOf(sumPrice()));

        } else {
            orderMessageLabel.setText("Please fill up all the information.");
        }
    }

    // To show menu inside the ComboBox
    public void showMenu() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String showMenu = "SELECT ItemName FROM item";

        try {
            Statement stat = connectDB.createStatement();
            ResultSet rs = stat.executeQuery(showMenu);
            while (rs.next()) {
                cust_order.getItems().addAll(rs.getString("ItemName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void setPaymentCtgry() {
        paymentCtgry.getItems().addAll("GoPay", "Bank Transfer", "Cash");
    }

    // Specify the range of number for the Quantity Spinner
    public void initSpinner() {
        item_qty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
    }


    // Initialize each column in the tableView
    public void initColumns() {
        item_name.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        item_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    // Add data in a form of row in a tableView one by one
    public void addRowsTableView() {

        String item = cust_order.getValue();
        Integer qty = item_qty.getValue();

        // Searching price for the item
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String findPrice = "SELECT * FROM item WHERE ItemName='" +item+ "'";

        try {
            Statement stat = connectDB.createStatement();
            ResultSet rs = stat.executeQuery(findPrice);

            while (rs.next()) {
                Integer price = rs.getInt("Price");
                Integer itemID = rs.getInt("ItemID");

                // Add the data into the observableList
                orderDetail.add(new orderDetails(itemID, item, price, qty, price * qty));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // Get all the data in the observable list
    public ObservableList<orderDetails> getOrderDetail() {
        return orderDetail;
    }

    // Update the total price
    public Integer sumPrice() {
        int totalPrice = 0;
        for (orderDetails order : addOrderTable.getItems()) {
            totalPrice = totalPrice + order.getSubtotal();
        }
        return totalPrice;
    }

    // Confirm Order Button
    public void confirmButtonOnAction(ActionEvent event) {

        String name = cust_name.getText();
        String phoneNum = cust_phone.getText();
        String address = cust_address.getText();
        String item = cust_order.getValue();
        Integer qty = item_qty.getValue();

        if (!name.isBlank() && !phoneNum.isBlank() && !address.isBlank()
                && !item.isBlank() && !qty.toString().isBlank() && !addOrderTable.getItems().isEmpty()) {
            validateCustomer();
        } else {
            orderMessageLabel.setText("There is no order input");
        }
    }

    public void deleteButtonOnAction(ActionEvent event) {
        addOrderTable.getItems().removeAll(addOrderTable.getSelectionModel().getSelectedItem());
        totalPriceLabel.setText(String.valueOf(sumPrice()));
    }

    public void newCustomer(){

        String name = cust_name.getText();
        String phoneNum = cust_phone.getText();
        String address = cust_address.getText();

        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String insertNewCustomer = "INSERT into customer values (null,"
                + "'"+name+"', '"+phoneNum+"', '"+address+"')";

        try {
            Statement stat = connectDB.createStatement();
            stat.executeUpdate(insertNewCustomer);
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void validateCustomer() {

        String name = cust_name.getText();
        String phoneNum = cust_phone.getText();
        String address = cust_address.getText();

        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String verifyCust = "SELECT count(1) FROM customer WHERE Name='" + name + "' and PhoneNumber='"
                + phoneNum + "' and Address='" + address + "'";

        try {
            Statement stat = connectDB.createStatement();
            ResultSet rs = stat.executeQuery(verifyCust);

            while (rs.next()) {

                if (rs.getInt(1) == 1) {
                    System.out.println("old customer");
                    }
                else {
                    newCustomer();
                }
                addNewOrder();
                addOrderToObservableArrayList();
                addOrderDetails();
                newPayment();
                allBlank();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void addNewOrder() {

        String name = cust_name.getText();
        String phoneNum = cust_phone.getText();
        String address = cust_address.getText();

        // Searching for the customerID for the item
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String findCustomerID = "SELECT * FROM customer WHERE Name='" + name + "' and PhoneNumber='"
                + phoneNum + "' and Address='" + address + "'";

        try {
            Statement stat = connectDB.createStatement();
            ResultSet rs = stat.executeQuery(findCustomerID);

            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");

                int totalPrice = Integer.parseInt(totalPriceLabel.getText());

                String insertNewOrder = "INSERT into orders (OrderID, CustomerID, StaffID, OrderDate, Status, " +
                        "TotalPrice) VALUES (?,?,?,current_date ,?,?)";

                PreparedStatement pst = connectDB.prepareStatement(insertNewOrder);
                pst.setString(1, null);
                pst.setInt(2, customerID);
                pst.setInt(3, staff_data.get(0).getStaffID());
                pst.setString(4, "IN THE PROCESS");
                pst.setInt(5, totalPrice);

                pst.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void addOrderToObservableArrayList(){
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String addOrders = "SELECT * FROM orders WHERE OrderDate= current_date";

        try {
            Statement stat = connectDB.createStatement();
            ResultSet rs = stat.executeQuery(addOrders);

            while (rs.next()) {
                Integer orderID = rs.getInt("OrderID");
                Integer customerID = rs.getInt("CustomerID");
                Integer staffID = rs.getInt("StaffID");
                Date orderDate = rs.getDate("OrderDate");
                String status = rs.getString("Status");
                Integer totalPrice = rs.getInt("TotalPrice");

                order.add(new orders(orderID, customerID, staffID, orderDate, status, totalPrice));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void addOrderDetails(){

        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        try{
            String insertOrderDetails = "INSERT into order_details (OrderDetail, OrderID, ItemID, Qty) VALUES (?,?,?,?)";

            PreparedStatement pst = connectDB.prepareStatement(insertOrderDetails);

            for (int i = 0; i < orderDetail.size(); i++) {

                pst.setString(1, null);
                pst.setInt(2, order.get(order.size()-1).getOrderID());
                pst.setInt(3, orderDetail.get(i).getItemID());
                pst.setInt(4, orderDetail.get(i).getQty());

                pst.execute();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void newPayment(){
        String payment = paymentCtgry.getValue();

        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        try{
            String insertPayment = "INSERT into payment (PaymentID, OrderID, " +
                    "PaymentCtgry, Status) VALUES (?,?,?,?)";

            PreparedStatement pst = connectDB.prepareStatement(insertPayment);

            pst.setString(1, null);
            pst.setInt(2, order.get(order.size()-1).getOrderID());
            pst.setString(3,payment);
            pst.setString(4, "UNPAID");

            pst.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void allBlank(){
        addOrderTable.getItems().removeAll(orderDetail);
        cust_order.valueProperty().set(null);
        cust_address.setText(null);
        cust_phone.setText(null);
        cust_name.setText(null);
        initSpinner();
        totalPriceLabel.setText(null);
    }

    public void logOutButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/login.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root, 600, 400));
            loginStage.show();

            Stage stage = (Stage) delete_button.getScene().getWindow();
            stage.close();

        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void profileButtonOnAction(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/profile.fxml"));
            Parent root = loader.load();

            //The following both lines are the only addition we need to pass the arguments
            profileController controller = loader.getController();
            controller.setProfile(staff_data.get(0).getStaffID(), staff_data.get(0).getName(), staff_data.get(0).getPhone(),
                    staff_data.get(0).getAddress(), staff_data.get(0).getEmail());

            Stage profileStage = new Stage();
            profileStage.setScene(new Scene(root, 350, 400));
            profileStage.show();

        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void aboutButtonOnAction(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/about.fxml"));
            Parent root = loader.load();

            Stage profileStage = new Stage();
            profileStage.setScene(new Scene(root, 350, 200));
            profileStage.show();

        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
