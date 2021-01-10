package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class insightTabController implements Initializable {


    @FXML
    private TableView<insights> insightTableView;
    @FXML
    private TableColumn<?, ?> monthColumn;
    @FXML
    private TableColumn<?, ?> incomeColumn;
    @FXML
    private TableColumn<?, ?> revenueColumn;

    private ObservableList<insights> insight = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initColumns();
        addRowsTableView();

        insightTableView.setItems(getInsight());
    }

    public void initColumns() {
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        incomeColumn.setCellValueFactory(new PropertyValueFactory<>("income"));
        revenueColumn.setCellValueFactory(new PropertyValueFactory<>("revenue"));

    }

    // Add data in a form of row in a tableView one by one
    public void addRowsTableView() {

        // Searching data
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String findPrice = "SELECT MONTHNAME(OrderDate) AS group_month, SUM(Price*Qty) AS income, SUM((Price-Cost)*Qty) AS revenue FROM item, order_details, orders WHERE " +
                "order_details.ItemID = item.ItemID and order_details.OrderID = orders.OrderID GROUP BY group_month";

        try {
            Statement stat = connectDB.createStatement();
            ResultSet rs = stat.executeQuery(findPrice);

            while (rs.next()) {
                String monthName= rs.getString("group_month");
                Integer incomeMonthly = rs.getInt("income");
                Integer revenueMonthly = rs.getInt("revenue");

                // Add the data into the observableList
                insight.add(new insights(monthName, incomeMonthly, revenueMonthly));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // Get all the data in the observable list
    public ObservableList<insights> getInsight() {
        return insight;
    }

}

