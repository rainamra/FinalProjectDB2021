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
import java.util.Date;
import java.util.ResourceBundle;

public class invoiceDetailController implements Initializable {

    @FXML
    private Text orderID;
    @FXML
    private TableView<invoiceDetails> orderDetailTableView;
    @FXML
    private TableColumn<?, ?> item;
    @FXML
    private TableColumn<?, ?> price;
    @FXML
    private TableColumn<?, ?> qty;
    @FXML
    private TableColumn<?, ?> subtotal;
    @FXML
    private Text orderDate;
    @FXML
    private Text custName;
    @FXML
    private Text custPhoneNum;
    @FXML
    private Text custAddress;
    @FXML
    private Text totalPrice;
    @FXML
    private Text payCategory;

    private ObservableList<invoiceDetails> detail = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initColumns();
        orderDetailTableView.setItems(getDetail());
    }

    public void initColumns() {
        item.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    public ObservableList<invoiceDetails> getDetail() {
        return detail;
    }

    public void setInvoiceDetail(Integer id, Date date, String name, String address, Integer total){
        orderID.setText(String.valueOf(id));
        custName.setText(name);
        custAddress.setText(address);
        totalPrice.setText(String.valueOf(total));
        orderDate.setText(String.valueOf(date));


        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        Integer order_id = Integer.valueOf(orderID.getText());

        String searchData = "SELECT d.Qty, i.ItemName, i.Price, c.PhoneNumber, p.paymentCtgry  " +
                "FROM order_details d, item i, customer c, orders o, payment p WHERE d.ItemID = i.ItemID and " +
                "d.OrderID = '"+order_id+"' and o.OrderID = '"+order_id+"' and p.OrderID='"+order_id+"' and o.CustomerID = c.CustomerID ";

        try {
            Statement stat = connectDB.createStatement();
            ResultSet rs = stat.executeQuery(searchData);

            while (rs.next()) {
                Integer qty = rs.getInt("d.Qty");
                String itemName = rs.getString("i.itemName");
                Integer itemPrice = rs.getInt("i.Price");
                String phone = rs.getString("c.phoneNumber");
                String payment = rs.getString("p.paymentCtgry");

                payCategory.setText(payment);
                custPhoneNum.setText(phone);
                detail.add(new invoiceDetails(itemName,itemPrice,qty,itemPrice*qty));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
