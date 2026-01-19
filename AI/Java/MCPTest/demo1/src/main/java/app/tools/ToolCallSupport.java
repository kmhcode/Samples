package app.tools;

import java.util.List;

import app.helpers.McpStdioAdapter;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpServerFeatures.SyncToolSpecification;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;

public class ToolCallSupport {
    
    public static SyncToolSpecification forQuotePrice(Sales handler) {
        var schema = """
        {
            "type": "object",
            "id": "urn:jsonschema:Operation",
            "properties": {
                "item": {
                    "type": "string",
                    "description": "Singularized name of item in lower-case."
                },
                "quantity": {
                    "type": "integer",
                    "description": "Number of items."
                }                        
            }
        }
        """;
        return McpServerFeatures.SyncToolSpecification.builder()
            .tool(McpSchema.Tool.builder()
                .name("quote_price")
                .description("Gets total price in purchase of an available item")
                .inputSchema(McpStdioAdapter.JSON_MAPPER, schema)
                .build()
            )
            .callHandler((exchange, request) -> {
                var arguments = request.arguments();
                var item = arguments.get("item").toString();
                var quantity = Integer.parseInt(arguments.get("quantity").toString());
                try{
                    var result = handler.quotePrice(item, quantity);
                    return CallToolResult.builder()
                        .content(List.of(new McpSchema.TextContent(McpStdioAdapter.JSON_MAPPER.writeValueAsString(result))))
                        .build();
                }catch(Exception e){
                    return CallToolResult.builder()
                        .isError(true)
                        .build();
                }
        })
        .build();
    }

}
