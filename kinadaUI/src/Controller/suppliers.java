package Controller;

public class suppliers {

    private Integer supplierID;
    private String supp_name;
    private String phone_num;
    private String supp_address;

    public suppliers(Integer supplierID, String supp_name, String phone_num, String supp_address) {
        this.supplierID = supplierID;
        this.supp_name = supp_name;
        this.phone_num = phone_num;
        this.supp_address = supp_address;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupp_name() {
        return supp_name;
    }

    public void setSupp_name(String supp_name) {
        this.supp_name = supp_name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getSupp_address() {
        return supp_address;
    }

    public void setSupp_address(String supp_address) {
        this.supp_address = supp_address;
    }
}
