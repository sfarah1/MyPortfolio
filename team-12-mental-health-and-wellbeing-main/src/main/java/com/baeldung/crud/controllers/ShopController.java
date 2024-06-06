package com.baeldung.crud.controllers;

import com.baeldung.crud.entities.Product;
import com.baeldung.crud.repositories.ProductRepository;
import com.baeldung.crud.service.ProductService;
import com.baeldung.crud.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;


@Controller
public class ShopController {

    @Autowired
    private ProductService productService; // To access data from the ProductService.java file

    @Autowired
    private ProductRepository productRepository; // To access data from the ProductRespository.java file

    @Autowired
    private ShoppingCartService shoppingCartService; // To access data from the ShoppingCartService.java file

    // This will display the shop.html page along with data from the database
    @GetMapping("/shop")
    public String showShop(Model model) {
        List<Product> products = productRepository.findAll();
        // This will find products in the database and display it to the shop page
        model.addAttribute("products", products);
        return "shop"; // This will return to the shop page
    }

    // Adding items to cart
    @PostMapping("shop/addToCart")
    public String addToCart(HttpServletRequest request, Model model, @RequestParam("id") Integer id,
                            @RequestParam("quantity") Integer quantity,
                            @RequestParam("size") String size) {
        //sessionToken
        String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
        if(sessionToken == null) {
            sessionToken = UUID.randomUUID().toString();
            request.getSession().setAttribute("sessionToken", sessionToken);
            shoppingCartService.addShoppingCartFirstTime(id, sessionToken, quantity, size);
        }
        else {
            shoppingCartService.addToExistingShoppingCart(id, sessionToken, quantity, size);
        }

        return "redirect:/shop";
    }

    //Shoppingcart View
    @GetMapping("shop/shoppingCart")
    public String showShoppingCartView(HttpServletRequest request, Model model) { // Viewing shopping cart
        return "shoppingCart";
    }

}
