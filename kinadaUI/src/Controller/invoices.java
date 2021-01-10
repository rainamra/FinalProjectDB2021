package Controller;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.sql.Connection;

import java.sql.Statement;
import java.util.Date;

public class invoices {
    private Integer order_id;
    private Date date;
    private String cust_name;
    private String order_address;
    private Integer payment_id;
    private Integer total_price;
    private ComboBox payment_stat;
    private ComboBox order_stat;
    private Button update;

    public invoices(Integer order_id, Date date, String cust_name, String order_address,
                    Integer payment_id, Integer total_price, ComboBox payment_stat, ComboBox order_stat, Button button)
    {
        this.order_id = order_id;
        this.date = date;
        this.cust_name = cust_name;
        this.order_address = order_address;
        this.payment_id = payment_id;
        this.total_price = total_price;
        this.payment_stat = payment_stat;
        this.order_stat = order_stat;
        this.update = new Button("UPDATE");

        update.setOnAction(e -> {
            for (invoices v: invoiceTabController.invoice){
                if (v.getUpdate() == update){

                    DatabaseConnection connect = new DatabaseConnection();
                    Connection connectDB = connect.getConnection();

                    String findPrice = " UPDATE orders, payment SET orders.Status ='"+v.getOrder_stat().getValue()+"', " +
                            "payment.Status ='"+v.getPayment_stat().getValue()+"' WHERE orders.OrderID ="+v.getOrder_id()+
                            " and payment.OrderID="+v.getOrder_id()+"";

                    try {
                        Statement stat = connectDB.createStatement();
                        stat.executeUpdate(findPrice);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        ex.getCause();
                    }
                }
            }
        });
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public Integer getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Integer payment_id) {
        this.payment_id = payment_id;
    }

    public Integer getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
    }

    public ComboBox getPayment_stat() {
        return payment_stat;
    }

    public void setPayment_stat(ComboBox payment_stat) {
        this.payment_stat = payment_stat;
    }

    public ComboBox getOrder_stat() {
        return order_stat;
    }

    public void setOrder_stat(ComboBox order_stat) {
        this.order_stat = order_stat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }
}
