package model;

import java.time.LocalDateTime;

public class Sales {
    // Atributos
    private int salesId;
    private Client customer;
    private Product product;
    private int quantity;
    private LocalDateTime dateOfSale;

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
