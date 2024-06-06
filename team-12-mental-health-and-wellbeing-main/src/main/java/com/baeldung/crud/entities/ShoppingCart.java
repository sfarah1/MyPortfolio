package com.baeldung.crud.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Specifying datatypes for columns in the database
    private int id; // The primary key for product identifier with the value of integar
    @Temporal(TemporalType.DATE)
    private Date date; // This will add the date of when the product is purchased
    @Transient
    private Double totalPrice;
    @Transient
    private int itemsNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CartItem> items = new HashSet<CartItem>();


    private String sessionToken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        Double sum = 0.0;
        for(CartItem item : this.items) {
            sum = sum + item.getProduct().getPrice();
        }
        return sum;
    }



    public int getItemsNumber() {
        return this.items.size();
    }

    public void setItemsNumber(int itemsNumber) {
        this.itemsNumber = itemsNumber;
    }

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }

    public String getTokenSession() {
        return sessionToken;
    }

    public void setTokenSession(String tokenSession) {
        this.sessionToken = sessionToken;
    }

}
