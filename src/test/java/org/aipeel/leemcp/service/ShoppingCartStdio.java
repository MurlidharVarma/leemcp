package org.aipeel.leemcp.service;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.Map;

public class ShoppingCartStdio {

    public static void main(String[] args) {
        var stdioParams = ServerParameters.builder("java")
                .args("-Dspring.profiles.active=stdio","-jar", "./target/leemcp-1.0.0.jar")
                .build();

        var transport = new StdioClientTransport(stdioParams);
        var client = McpClient.sync(transport).build();

        client.initialize();

        // List and demonstrate tools
        McpSchema.ListToolsResult toolsList = client.listTools();
        System.out.println("Available Tools = " + toolsList);

        // Add item to cart & list
        McpSchema.CallToolResult shoppingCartAddItemTool = client.callTool(new McpSchema.CallToolRequest("addItemToCart", Map.of("itemName", "Apple", "quantity", 2, "unit","kg")));
		System.out.println("Adding Item: " + shoppingCartAddItemTool);

        McpSchema.CallToolResult listCartItemsTool = client.callTool(new McpSchema.CallToolRequest("listCartItems",Map.of()));
		System.out.println("List Cart = " + listCartItemsTool);

        // Add another item to cart and list
        McpSchema.CallToolResult shoppingCartAddItemTool2 = client.callTool(new McpSchema.CallToolRequest("addItemToCart", Map.of("itemName", "Biscuit", "quantity", 10, "unit","packets")));
        System.out.println("Adding Item: " + shoppingCartAddItemTool2);

        McpSchema.CallToolResult listCartItemsTool2 = client.callTool(new McpSchema.CallToolRequest("listCartItems",Map.of()));
        System.out.println("List Cart = " + listCartItemsTool2);

        // find an item in the cart
        McpSchema.CallToolResult findItemInCart = client.callTool(new McpSchema.CallToolRequest("findItemInCart",Map.of("itemName", "Apple")));
        System.out.println("Find Item in Cart = " + findItemInCart);

        // Delete an item from cart & list cart
        McpSchema.CallToolResult deleteItemFromCart = client.callTool(new McpSchema.CallToolRequest("removeItemFromCart",Map.of("itemName", "Apple")));
        System.out.println("Remove Item from Cart = " + deleteItemFromCart);

        McpSchema.CallToolResult listCartItemsTool3 = client.callTool(new McpSchema.CallToolRequest("listCartItems",Map.of()));
        System.out.println("List Cart = " + listCartItemsTool3);

        client.closeGracefully();
    }
}
