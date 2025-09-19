package org.aipeel.leemcp.service;

import org.aipeel.leemcp.service.shoppingcart.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTests {

    ShoppingCart shoppingCart;

    @BeforeEach
    public void init(){
        shoppingCart = new ShoppingCart();
        ShoppingCart.cart = new ArrayList<>();
    }

    @Test
    @DisplayName("Add an item to cart")
    public void testAddingItemToCart(){
        String response = shoppingCart.addItemToCart("Apple", 2, "kg");
        assertEquals("Apple of quantity 2 kg added to cart",response);
    }

    @Test
    @DisplayName("Add multiple items to cart")
    public void testAddingMultipleItemToCart(){
        shoppingCart.addItemToCart("Apple", 2, "kg");
        shoppingCart.addItemToCart("Biscuit", 5, "packets");

        assertEquals(2,ShoppingCart.cart.size());
    }

    @Test
    @DisplayName("List all items in cart")
    public void testListAllItemsInCart(){
        shoppingCart.addItemToCart("Apple", 2, "kg");
        shoppingCart.addItemToCart("Biscuit", 5, "packets");

        assertEquals("Apple : 2 kg\n\rBiscuit : 5 packets\n\r",shoppingCart.listCartItems());
    }

    @Test
    @DisplayName("Remove an item from cart")
    public void testRemoveItemFromCart(){
        shoppingCart.addItemToCart("Apple", 2, "kg");
        shoppingCart.addItemToCart("Biscuit", 5, "packets");
        shoppingCart.removeItemFromCart("Biscuit");
        assertEquals(1,ShoppingCart.cart.size());
        assertEquals("Apple", ShoppingCart.cart.get(0).itemName());
    }

    @Test
    @DisplayName("Find an item in cart")
    public void testFindItemInCart(){
        shoppingCart.addItemToCart("Apple", 2, "kg");
        shoppingCart.addItemToCart("Biscuit", 5, "packets");
        String response = shoppingCart.findItemInCart("Biscuit");
        assertEquals(2,ShoppingCart.cart.size());
        assertEquals("Biscuit of quantity 5 packets is available in the cart", response);
    }
}
