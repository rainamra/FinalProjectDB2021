package Controller;

public class ingredients {
    private Integer ingredientID;
    private String ingredientName;
    private Integer ingredientPrice;

    public ingredients(Integer ingredientID, String ingredientName, Integer ingredientPrice) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    public Integer getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(Integer ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Integer getIngredientPrice() {
        return ingredientPrice;
    }

    public void setIngredientPrice(Integer ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }
}
