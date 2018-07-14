package in.definex.Joke;

import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.String.Strings;

import java.util.Random;

/**
 * tellajoke command of JokeFeature
 * Tells a random joke when called.
 *
 * Made in Exercise of Ep.2 Command and Feature
 * Changed in Exercise of Ep.3 Database
 */
public class TellAJokeCommand extends Command {

    private static final String keyword = "tellajoke";

    /**
     * Keyword for the command is "tellajoke".
     * it doesn't take any arguments.
     * Any client (even unregsitered) can call this command
     */
    public TellAJokeCommand() {
        super(keyword, 0, Client.Role.Unregistered);
    }

    /**
     *
     * @param client client who called the command
     * @param strings arugments
     * @return gets the joke from database and returns it,
     */
    @Override
    protected String compute(Client client, String[] strings) {
        return JokeDatabase.GetRandomJoke();
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
