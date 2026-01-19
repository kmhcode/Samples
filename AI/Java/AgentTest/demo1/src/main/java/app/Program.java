package app;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import app.helpers.OpenAIAdapter;

public class Program {

    public static void main(String[] args) throws Exception {
        System.setProperty("slf4j.internal.verbosity", "ERROR");
        var settings = new Properties();
        try(var config = new FileInputStream("application.properties")){
            settings.load(config);
        }
        var ai = new OpenAIAdapter(settings);
        var agent = ai.createAgentWithData(
            """
            You are an inventory expert, you provide information about items using given data.
            Respond in plain text only without markdown   
            """, 
            Files.readString(Path.of("medstore.csv"))
        );
        var reply = agent.run(args[0]);
        System.out.println(reply);
    }

}

