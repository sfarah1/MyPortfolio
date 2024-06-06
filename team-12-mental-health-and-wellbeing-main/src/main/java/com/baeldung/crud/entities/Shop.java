package com.baeldung.crud.entities;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Lob
    private String imageURL;

    private String email;

    private int phone;

    private int item_price;

    public Shop() {}

    public Shop(String name, int item_price, String email) {
        this.name = name;
        this.imageURL = "";
        this.email = email;
        this.phone = phone;
    }

    public Shop(String name, int item_price, String imageURL, String email, int phone) {
        this.name = name;

        this.item_price = item_price;
        this.imageURL = imageURL;
        this.email = email;
        this.phone = phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.item_price = item_price;
    }

    public String getName() {
        return name;
    }

    public int getItemPrice() {
        return item_price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Shop{" + "id=" + id + ", name=" + name + ", price=" + item_price + '}';
    }

}
