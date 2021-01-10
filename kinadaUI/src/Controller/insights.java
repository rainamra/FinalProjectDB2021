package Controller;

public class insights {
    private String month;
    private Integer income;
    private Integer revenue;

    public insights(String month, Integer income, Integer revenue) {
        this.month = month;
        this.income = income;
        this.revenue = revenue;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }
}
