package org.aipeel.leemcp.service.shoppingcart;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCart {

    public static List<ItemRecord> cart = new ArrayList<>();

    @Tool(description = "List all items in the cart")
    public String listCartItems (){
        StringBuilder bf = new StringBuilder();

        for (ItemRecord item: cart){
            bf.append(item.itemName())
                    .append(" : ")
                    .append(item.quantity())
                    .append(" ")
                    .append(item.unit())
                    .append("\n\r");
        }

        return bf.toString();
    }

    @Tool(description = "add item to cart. Input is item name, quantity and unit. Unit is measure of quantity e.g number or kilogram or packets")
    public String addItemToCart (String itemName, Integer quantity, String unit){
        cart.add(new ItemRecord(itemName,quantity,unit));
        return String.format("%s of quantity %d %s added to cart",itemName, quantity, unit);
    }

    @Tool(description = "remove item from cart. Input is item name")
    public String removeItemFromCart (String itemName){
        ItemRecordPosition rec = findItem(itemName);
        ItemRecord item = rec.itemRecord();
        int pos = rec.index();
        String response =  String.format("%s of quantity %d %s removed from cart",item.itemName(), item.quantity(), item.unit());
        cart.remove(pos);
        return response;
    }

    @Tool(description = "find an item in the cart. Input is item name")
    public String findItemInCart(String itemName){
        ItemRecordPosition rec = findItem(itemName);
        ItemRecord item = rec.itemRecord();
        return String.format("%s of quantity %d %s is available in the cart",item.itemName(), item.quantity(), item.unit());
    }

    private ItemRecordPosition findItem(String itemName){
        ItemRecordPosition currItemPosition = null;
        int position = -1;
        ItemRecord currItem = null;
        for(int i=0; i<cart.size(); i++){
            currItem = cart.get(i);
            if(currItem.itemName().equalsIgnoreCase(itemName)){
                position = i;
                break;
            }
        }
        return new ItemRecordPosition(currItem,position);
    }
}
