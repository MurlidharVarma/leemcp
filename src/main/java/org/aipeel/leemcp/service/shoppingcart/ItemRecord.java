package org.aipeel.leemcp.service.shoppingcart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ItemRecord (@JsonProperty("itemName") String itemName, @JsonProperty("quantity") Integer quantity, @JsonProperty("unit") String unit){};
