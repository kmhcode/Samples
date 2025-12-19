package app;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

public class DiscountAgent {
    
    @Tool("Returns percentage discount offered in purchase of an item")
    public float getBulkDiscount(@P("Number of items") int quantity, @P("Name of item") String item) {
        System.out.printf("Determining discount percentage for %s...%n", item);
        return switch(Integer.valueOf(quantity)){
            case Integer n when n > 1 && n < 10 -> 5.0f;
            case Integer n when n >= 10 -> 7.5f;
            default -> 0;
        };
    }
}