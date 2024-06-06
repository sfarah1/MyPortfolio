package com.baeldung.crud.service;

import com.baeldung.crud.entities.Product;
import com.baeldung.crud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // To save datatypes into the database
    public void saveProductToDB(MultipartFile file, String name, String description, int price) {


        Product product = new Product();
        //StringUtils is used to check for blank or null Strings.
        String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // To save the image into the database
        if (fileName.contains("..")) // If the filename contains a ".." in its path
        {
            // Then it should deliver a error message as defined below.
            System.out.println("not a valid file");
        }
        try
        {
            //Base64 represents binary data in a printable string format
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        }   catch (IOException e) {
            // This occurs an exception when an IO operation fails. IOException will print out the root cause of the failure.
            e.printStackTrace();
        }
        product.setName(name); // Saving the name into db
        product.setPrice(price); // Saving the price into db
        product.setDescription(description); // Saving the description into db
        productRepository.save(product); // Save all data describe above as an implementation of the JPA Repository extended in the Product Repository

    }

    // To get all products data previously
    public List<Product> getProducts(){
        return productRepository.findAll(); // To find all the values as annotated in the Product Repository
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }
}
