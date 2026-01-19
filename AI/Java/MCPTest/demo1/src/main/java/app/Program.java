package app;

import app.helpers.McpStdioAdapter;
import app.tools.Sales;
import app.tools.ToolCallSupport;

public class Program {
    
    public static void main(String[] args) throws Exception {
        System.setProperty("slf4j.internal.verbosity", "ERROR");
        McpStdioAdapter.startServerWithTools(
            ToolCallSupport.forQuotePrice(new Sales())
        );
    }
}
