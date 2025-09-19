package org.aipeel.leemcp.service.shoppingcart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ItemRecordPosition (ItemRecord itemRecord, Integer index){};