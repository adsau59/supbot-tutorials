package in.definex.UrbanDictionary;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.String.Strings;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UDCommand extends Command {

    private static final String keyword = "ud";

    private static final String responseTemplate =
            "%s\n\n" +
                    "Definition:\n" +
                    "%s\n\n" +
                    "Example:\n" +
                    "%s\n\n" +
                    "Link:\n" +
                    "%s";


    public UDCommand() {
        super(keyword, 1, Client.Role.Member);
    }


    @Override
    protected String compute(Client client, String[] strings) {

        HttpClient httpClient = HttpClientBuilder.create().build();

        String word = strings[0].replace(" ", "%20");

        HttpGet request = new HttpGet(String.format("http://api.urbandictionary.com/v0/define?term=%s", word));

        try {
            HttpResponse response = httpClient.execute(request);

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(
                            response.getEntity().getContent()
                    )
            );

            String line = "";
            String jsonString = "";
            while((line = rd.readLine()) != null){
                //System.out.println(line);
                jsonString += line + "\n";
            }

            JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();

            if(jsonObject.get("result_type").getAsString().equals("no_results") )
                return "Word not found in Urban Dictionary";

            JsonObject meaning = jsonObject.getAsJsonArray("list").get(0).getAsJsonObject();

            return String.format(responseTemplate,
                    strings[0],
                    meaning.get("definition").getAsString(),
                    meaning.get("example").getAsString(),
                    meaning.get("permalink").getAsString()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "Sorry, some error has occurred";
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                String.format("%s%s [word]", Strings.commandPrefix, keyword),
                String.format("%s%s TIL", Strings.commandPrefix, keyword),
                "Shows the meaning of the word."
        );
    }
}
