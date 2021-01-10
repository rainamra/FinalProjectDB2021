package Controller;

public class orderDetails {

    private Integer itemID;
    private String itemName;
    private Integer price;
    private Integer qty;
    private Integer subtotal;

    public orderDetails(Integer itemID, String itemName, Integer price, Integer qty, Integer subtotal) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.qty = qty;
        this.subtotal = subtotal;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQty() {
        return this.qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

}
