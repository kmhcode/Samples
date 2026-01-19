package app.tools;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import app.models.Inventory;

@Component
public class Sales {
    
    private final Inventory model = new Inventory();

    @McpTool(name = "quote_price", description = "Gets total price in purchase of an available item")
    public Double quotePrice(@McpToolParam(description = "Singularized name of item in lower-case") String item, @McpToolParam(description = "Number of items") int quantity) {
        var info = model.store().get(item);
        if(info != null) {
            double discount = quantity < 10 ? 0 : 0.05;
            return quantity * info.unitPrice() * (1 - discount);
        }
        return null;
    }
}
