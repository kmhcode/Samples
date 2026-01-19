package app.helpers;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.modelcontextprotocol.json.jackson.JacksonMcpJsonMapper;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpServerFeatures.SyncToolSpecification;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema.ServerCapabilities;

public class McpStdioAdapter {
    
    public static final JacksonMcpJsonMapper JSON_MAPPER = new JacksonMcpJsonMapper(new ObjectMapper());

    public static void startServerWithTools(SyncToolSpecification... tools) {
        var transportProvider = new StdioServerTransportProvider(JSON_MAPPER); 
        McpServer.sync(transportProvider)
            .capabilities(ServerCapabilities.builder()
                .tools(true)
                .build()
            )
            .tools(List.of(tools))
            .build();
    }
}
