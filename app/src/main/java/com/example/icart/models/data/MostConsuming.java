package com.example.icart.models.data;

public class MostConsuming {

    private String elementName , times , total;


    public MostConsuming(String elementName, String times, String total) {
        this.elementName = elementName;
        this.times = times;
        this.total = total;
    }


    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
