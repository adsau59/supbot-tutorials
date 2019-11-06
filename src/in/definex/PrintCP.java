package in.definex;

import in.definex.ChatSystem.Bubble;
import in.definex.ChatSystem.ChatProcessor;
import in.definex.ChatSystem.Client;
import in.definex.Console.Log;

public class PrintCP extends ChatProcessor {
    /**
     * Constructor
     *
     * @param multithreaded set to true if want to use multithreading.
     */
    public PrintCP() {
        super(true);
    }

    @Override
    public void task(Bubble bubble, Client client) {

        Log.r(client.getName() + ": "+ bubble.getText());

    }
}
