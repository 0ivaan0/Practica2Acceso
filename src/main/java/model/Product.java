package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {

    private int id;
    private String name;
    private String description;
    private int stock;
    private double price;
    private boolean available;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public Product() {

    }

    public Product(int id, String name, String description, int stock, double price, boolean available, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.available = available;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Product(String name, String description, int stock, double price, boolean available, LocalDateTime createDate, LocalDateTime updateDate) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.available = available;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", available=" + available +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && stock == product.stock && Double.compare(price, product.price) == 0 && available == product.available && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(createDate, product.createDate) && Objects.equals(updateDate, product.updateDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
