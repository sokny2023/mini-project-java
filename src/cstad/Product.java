package cstad;

public  class Product {
    private Integer id;
    private String name;
    private Double unitPrice;
    private Integer qty;
    private String date;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product(Integer id, String name, Double unitPrice, Integer qty, String date) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Integer getQty() {
        return qty;
    }

    public String getDate() {
        return date;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void display() {
        System.out.println(id + "\t" + name + "\t" + unitPrice + "\t" + qty + "\t" + date);
    }

    @Override
    public String toString() {
        return String.format("%d\t%s\t%.2f\t%d\t%s", id, name, unitPrice, qty, date);
    }

}