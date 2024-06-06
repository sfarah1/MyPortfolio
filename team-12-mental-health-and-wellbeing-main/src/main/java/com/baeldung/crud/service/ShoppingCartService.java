package com.baeldung.crud.service;

import com.baeldung.crud.entities.CartItem;
import com.baeldung.crud.entities.Product;
import com.baeldung.crud.entities.ShoppingCart;
import com.baeldung.crud.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    public ShoppingCart addShoppingCartFirstTime(Integer id, String sessionToken, Integer quantity, String size) {
        ShoppingCart shoppingCart = new ShoppingCart();
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setSize(size);
        cartItem.setDate(new Date());
        cartItem.setProduct(productService.getProductById(id));
        shoppingCart.getItems().add(cartItem);
        shoppingCart.setTokenSession(sessionToken);
        shoppingCart.setDate(new Date());
        return shoppingCartRepository.save(shoppingCart);

    }

    public ShoppingCart addToExistingShoppingCart(int id, String sessionToken, int quantity, String size) {

        ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken(sessionToken);
        Product p = productService.getProductById(id);
        Boolean productDoesExistInTheCart = false;
        if (shoppingCart != null) {
            Set<CartItem> items = shoppingCart.getItems();
            for(CartItem item : items) {
                if(item.getProduct().equals(p)) {  // Check if product already exists
                    productDoesExistInTheCart = true;
                    item.setQuantity(item.getQuantity() + quantity); // setting old quantity with the new quantity
                    item.setSize(item.getSize() + size);
                    shoppingCart.setItems(items);
                    return shoppingCartRepository.saveAndFlush(shoppingCart);
                }
            }
        }
        if (!productDoesExistInTheCart && (shoppingCart != null)) {
            CartItem cartItem1 = new CartItem();
            cartItem1.setDate(new Date());
            cartItem1.setQuantity(quantity);
            cartItem1.setProduct(p);
            shoppingCart.getItems().add(cartItem1);
            return shoppingCartRepository.saveAndFlush(shoppingCart);
        }

        return this.addShoppingCartFirstTime(id, sessionToken, quantity, size);

    }

}
