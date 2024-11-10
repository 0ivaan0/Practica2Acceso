package model;

import java.time.LocalDateTime;

public class Sales {
    // Atributos
    private int salesId;
    private Client customer;
    private Product product;
    private int quantity;
    private LocalDateTime dateOfSale;

    public Sales(){

    }

    public Sales(int salesId, Client customer, Product product, int quantity, LocalDateTime dateOfSale) {
        this.salesId = salesId;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.dateOfSale = dateOfSale;
    }

    public Sales(Client customer, Product product, int quantity, LocalDateTime dateOfSale) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.dateOfSale = dateOfSale;
    }

    // Métodos get y set
    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public Client getCustomer() {
        return customer;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(LocalDateTime dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    // Método toString
    @Override
    public String toString() {
        return "Sales{" +
                "salesId=" + salesId +
                ", customer=" + customer +
                ", product=" + product +
                ", quantity=" + quantity +
                ", dateOfSale=" + dateOfSale +
                '}';
    }
}
