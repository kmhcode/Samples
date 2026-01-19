package app.tools;

import app.models.Inventory;
import app.models.ItemInfo;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

public class Shop {
    
    private final Inventory model = new Inventory();

    @Tool("Gets unit-price and stock-level of an item.")
    public ItemInfo fetchItem(@P("Singularized name of item.") String item) {
        return model.store().get(item.toLowerCase());
    }

    @Tool("Gets percentage discount offered on bulk purchase.")
    public static float offerDiscount(@P("Number of items.") int quantity) {
        if(quantity >= 20)
            return 7.5f;
        if(quantity >= 10)
            return 5.0f;
        return 0;
    }
}
