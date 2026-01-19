package app.helpers;

import java.util.Properties;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class OpenAIAdapter {

    public interface ChatAgent {
        String run(String prompt);
    }

    private OpenAiChatModel model;

    public OpenAIAdapter(Properties settings) {
        model = OpenAiChatModel.builder()
            .modelName(settings.getProperty("openai.chat.model"))
            .baseUrl(settings.getProperty("openai.api.base-url"))
            .apiKey(settings.getProperty("openai.api.key"))
            .temperature(Double.valueOf(settings.getProperty("openai.chat.temperature", "0.7"))) // range: 0.0(pure logic) - 1.0(high creativity)                                                                     // creativity)
            .build();
    }

    public ChatAgent createAgentWithData(String message, String source) {
        return AiServices.builder(ChatAgent.class)
                .chatModel(model)
                .systemMessageProvider(id -> message + "\n\n" + source)
                .build();

    }

    public ChatAgent createAgentWithTools(String message, Object source) {
        return AiServices.builder(ChatAgent.class)
                .chatModel(model)
                .systemMessageProvider(id -> message)
                .tools(source)
                .build();

    }

}
