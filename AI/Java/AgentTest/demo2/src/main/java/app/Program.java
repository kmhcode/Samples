package app;

import java.io.FileInputStream;
import java.util.Properties;

import app.helpers.OpenAIAdapter;
import app.tools.Shop;

public class Program {

    public static void main(String[] args) throws Exception {
        System.setProperty("slf4j.internal.verbosity", "ERROR");
        var settings = new Properties();
        try(var config = new FileInputStream("application.properties")){
            settings.load(config);
        }
        var ai = new OpenAIAdapter(settings);
        var agent = ai.createAgentWithTools(
            """
            You are an inventory expert, you provide information about items using given data.
            Provide only the final result.   
            """, 
            new Shop()
        );
        var reply = agent.run(args[0]);
        System.out.println(reply);
    }

}

