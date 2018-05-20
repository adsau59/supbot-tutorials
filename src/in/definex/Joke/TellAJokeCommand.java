package in.definex.Joke;

import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.String.Strings;

import java.util.Random;

public class TellAJokeCommand extends Command {

    private static final String[] jokes = {
            "Can a kangaroo jump higher than a house? \n" +
                    "-\n" +
                    "Of course, a house doesn’t jump at all.",
            "Doctor: \"I'm sorry but you suffer from a terminal illness and have only 10 to live.\"\n" +
                    "\n" +
                    "Patient: \"What do you mean, 10? 10 what? Months? Weeks?!\"\n" +
                    "\n" +
                    "Doctor: \"Nine.\"",
            "A man asks a farmer near a field, “Sorry sir, would you mind if I crossed your field instead of going around it? You see, I have to catch the 4:23 train.”\n" +
                    "\n" +
                    "The farmer says, \"Sure, go right ahead. And if my bull sees you, you’ll even catch the 4:11 one.\"",
            "Anton, do you think I’m a bad mother?\n" +
                    "\n" +
                    "My name is Paul."
    };

    private static final String keyword = "tellajoke";

    public TellAJokeCommand() {
        super(keyword, 0, Client.Role.Unregistered);
    }

    @Override
    protected String compute(Client client, String[] strings) {
        return jokes[new Random().nextInt(jokes.length)];
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                String.format("%s%s", Strings.commandPrefix,keyword),
                String.format("%s%s", Strings.commandPrefix,keyword),
                "Tell a funny joke"
                );
    }
}
