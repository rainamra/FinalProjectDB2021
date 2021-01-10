package Controller;

public class invoiceDetails {
    private Integer price;
    private Integer qty;
    private Integer subtotal;
    private String itemName;

    public invoiceDetails(String itemName, Integer price, Integer qty, Integer subtotal) {
        this.qty = qty;
        this.price = price;
        this.subtotal = subtotal;
        this.itemName = itemName;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
