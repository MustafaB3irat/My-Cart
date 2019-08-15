package com.example.icart.models.data;

public class CategorySummary {

    private String categoryName, total;


    public CategorySummary(String categoryName, String total) {
        this.categoryName = categoryName;
        this.total = total;
    }

    public String getCategoryName() {

        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
