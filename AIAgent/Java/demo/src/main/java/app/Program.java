package app;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.Result;

public class Program {
    
    interface Assistant {
        Result<String> prompt(String question);
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("slf4j.internal.verbosity", "NONE");
        var model = OpenAiChatModel.builder()
            .modelName("default-llm")
            .baseUrl("http://iitdac.met.edu/openai/")
            .apiKey("not-required")
			.temperature(0.0) //range: 0.0(pure logic) - 1.0(high creativity)
            .build();
        var chatService = AiServices.builder(Assistant.class)
            .chatModel(model)
            .tools(new DiscountAgent())
            .build();
        var toolResults = chatService.prompt(args[0])
            .toolExecutions();
        if(toolResults.size() > 0){
            var reply = toolResults.get(0);
            System.out.println(reply.result());
        }
    }

}


//bash run "What will be the discount for 12 apples"
