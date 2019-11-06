package in.definex;

import in.definex.Action.Core.SendMessageAction;
import in.definex.ChatSystem.Bubble;
import in.definex.ChatSystem.ChatProcessor;
import in.definex.ChatSystem.Client;
import in.definex.Console.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class ProfanityWarnCP extends ChatProcessor {

    ProfanityWarnCP() {
        super(true);
    }

    private HashSet<String> badWords;

    @Override
    public void task(Bubble bubble, Client client) {

        if(badWords == null){

            badWords = new HashSet<>();

            try(BufferedReader bufferedReader = new BufferedReader(new FileReader("badWords.txt"))){

                String line;
                while ((line = bufferedReader.readLine()) != null){
                    badWords.add(line);
                }

            } catch (IOException e) {
                Log.e("Could not load bad words");
                badWords = null;
                return;
            }

        }

        String[] messageWords = bubble.getText().toLowerCase().split(" ");

        for(String s : messageWords)
            if(badWords.contains(s))
                Bot.getActionManager().add(new SendMessageAction(client.getChatGroup(), "You dirty mouth!!!"));

    }
}
