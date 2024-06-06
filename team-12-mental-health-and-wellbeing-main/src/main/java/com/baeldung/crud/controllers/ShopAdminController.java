package com.baeldung.crud.controllers;

import com.baeldung.crud.entities.Product;
import com.baeldung.crud.repositories.ProductRepository;
import com.baeldung.crud.service.CategoryService;
import com.baeldung.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ShopAdminController {

    @Autowired
    private ProductRepository productRepository; // To access data from the ProductRespository.java file

    @Autowired
    private ProductService productService; // To access data from the ProductService.java file

    @GetMapping("/admin/shop")
    public String adminHome() {
        return "shopadmin";
    } //This will display the shop admin page

    @GetMapping("/admin/shop/products")
    public String showProductView(Model model){
        List<Product> products = productRepository.findAll();
        // This will find products in the database and display it to the admin
        model.addAttribute("products", products);
        return "products"; // This will return to the admin product display page
    }

    // Request for the adding product page
    @GetMapping("/admin/shop/product/add")
    public String showAddProduct(){
        return "productAdd";
    }

    // Post Request for adding products to the database
    @PostMapping("/admin/shop/product/add")
    // Here data taken from the input fields in productAdd.html page will be added to the database
    public String saveProduct(@RequestParam("file") MultipartFile file,  //This will upload the image file to the database
                        @RequestParam("name") String name,  // This will upload product name
                        @RequestParam("price") int price,  // This will upload product price
                        @RequestParam("description") String description)  // This will upload product description.
    {
        productService.saveProductToDB(file, name, description, price);
        return "redirect:/admin/shop/products";  // After a product is added to db, this will redirect it back to the product display page product.html
    }

    // Deleting a Product
    @GetMapping("/admin/shop/products/deleteProd/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productRepository.deleteById(id);  //This will initiate deletion of a product from the db by its id
        return "redirect:/admin/shop/products";  // After a product is deleted from the db, this will redirect it back to the product display page product.html
    }

    //Update Page
    @GetMapping("/admin/shop/product/update")
    public String showProductUpdate(Model model) {
        List<Product> products = productRepository.findAll();  // This will find data regarding the products in the db and disply on the update
        model.addAttribute("products", products);
        return "productUpdate";
    }

    // Updating the name
    @PostMapping("admin/shop/product/update/name")
    public String updateName(@RequestParam("id") int id,
                             @RequestParam("newname") String name){
        Product product =new Product();
        product = productRepository.findById(id).get();
        // This will look for products in the database by their id
        product.setName(name); // Setting a new name
        productRepository.save(product); // saving new name to the repository
        return "redirect:/admin/shop/product/update"; // returning to the product upfate page
    }

    //Updating Price
    @PostMapping("admin/shop/product/update/price")
    public String updatePrice(@RequestParam("id") int id,
                              @RequestParam("newprice") int price){
        Product product =new Product();
        product = productRepository.findById(id).get();
        // This will look for products in the database by their id
        product.setPrice(price); // Setting new price
        productRepository.save(product);
        return "redirect:/admin/shop/product/update";
    }

    // Updating Description
    @PostMapping("admin/shop/product/update/description")
    public String updateDescription(@RequestParam("id") int id,
                              @RequestParam("newdescription") String description){
        Product product =new Product();
        product = productRepository.findById(id).get();
        // This will look for products in the database by their id
        product.setDescription(description); // Setting new description
        productRepository.save(product);
        return "redirect:/admin/shop/product/update";
    }

}
