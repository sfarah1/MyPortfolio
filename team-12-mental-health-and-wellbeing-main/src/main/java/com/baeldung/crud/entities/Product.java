package com.baeldung.crud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.*;

@Entity // To identify as an Entity class and import library from Java Persistence API
@Table(name = "product")    // To identify the table in the database used for this functionality. Without calling it, JPA would create a table itself named after the class name.
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Specifying datatypes for columns in the database
    private int id; // The primary key with the value of integar
    private String name; // The name field implemented as a String data type
    private int price;  // The price field implemented as an Integer data type
    private String description; // The description field implemented as a String data type
    @Column(columnDefinition = "MEDIUMBLOB") // The Image field defined as a MEDIUMBLOB. MediumBlob is a large binary object with a maximum length of 16777215 bytes or a storage of 16MB.
    private String image; // The image field will have its name saved as a string


    // The getters and setters for all the data values defined above
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
