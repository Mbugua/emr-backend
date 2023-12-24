package com.casestudy.emr.emr.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents an entity in a database table called "products".
 * It contains fields that store information about a product, such as its title,
 * description, batch number, quantity, expiration date, price, and availability
 * status.
 * The class also provides getter and setter methods for accessing and modifying
 * these fields.
 * 
 * Example Usage:
 * 
 * // Creating a new inventory object
 * Inventory inventory = new Inventory("Product Title", "Product Description",
 * "1234", 10.0, "2022-12-31", 9.99, true);
 * 
 * // Accessing and modifying inventory fields
 * String title = inventory.getTitle();
 * inventory.setQuantity(5.0);
 * 
 * // Printing inventory details
 * System.out.println(inventory.toString());
 * 
 * Methods:
 * 
 * - Inventory(): Default constructor for the Inventory class.
 * - Inventory(String title, String description, String batchNo, Double
 * quantity, String expirationDate, Double price, boolean isInstock):
 * Constructor that initializes the Inventory object with the given product
 * details.
 * - getId(): Returns the ID of the inventory item.
 * - getTitle(): Returns the title of the product.
 * - setTitle(String title): Sets the title of the product.
 * - getDescription(): Returns the description of the product.
 * - setDescription(String description): Sets the description of the product.
 * - getBatchNo(): Returns the batch number of the product.
 * - setBatchNo(String batchNo): Sets the batch number of the product.
 * - getQuantity(): Returns the quantity of the product.
 * - setQuantity(Double quantity): Sets the quantity of the product.
 * - getExpirationDate(): Returns the expiration date of the product.
 * - setExpirationDate(String expirationDate): Sets the expiration date of the
 * product.
 * - getPrice(): Returns the price of the product.
 * - setPrice(Double price): Sets the price of the product.
 * - isInstock(): Returns the availability status of the product.
 * - setInstock(Boolean isInstock): Sets the availability status of the product.
 * - toString(): Returns a string representation of the Inventory object.
 * 
 * Fields:
 * 
 * - id: Represents the ID of the inventory item.
 * - title: Represents the title of the product.
 * - description: Represents the description of the product.
 * - batchNo: Represents the batch number of the product.
 * - quantity: Represents the quantity of the product.
 * - expirationDate: Represents the expiration date of the product.
 * - price: Represents the price of the product.
 * - isInstock: Represents the availability status of the product.
 */
@Entity
@Table(name = "products")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "batch_no")
    private String batchNo;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_instock")
    private Boolean isInstock;

    public Inventory() {
    }

    public Inventory(String title, String description, String batchNo, Double quantity, String expirationDate,
            Double price, boolean isInstock) {
        this.title = title;
        this.description = description;
        this.batchNo = batchNo;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
        this.isInstock = isInstock;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean isInStock() {
        return isInstock;
    }

    public void setInStock(Boolean isInstock) {
        this.isInstock = isInstock;
    }

    @Override
    public String toString() {
        return "Inventory [id=" + id + ", title=" + title + ", description=" + description + ", batchNo=" + batchNo
                + ", quantity=" + quantity + ", expirationDate=" + expirationDate + ", price=" + price + ", isInstock="
                + isInstock + "]";
    }

}
