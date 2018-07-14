package in.definex.Joke;

import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.String.Strings;

/**
 * Command in JokeFeature
 * Adds a joke in the JokeDatabase when called
 *
 * Made in Exercise of Ep.3 Database
 */
public class AddAJokeCommand extends Command {

    private static String keyword = "addajoke";

    /**
     * Keyword for the command is "addajoke".
     * It takes 1 argument, the joke string.
     * Clients with Role Coadmin or higher will be able to use this command.
     */
    public AddAJokeCommand() {
        super(keyword, 1, Client.Role.CoAdmin);
    }

    /**
     * Calls AddJoke function in JokeDatabase
     *
     * @param client client who called the command
     * @param strings arguments
     * @return response according to the return of AddJoke
     */
    @Override
    protected String compute(Client client, String[] strings) {

        if(JokeDatabase.AddJoke(strings[0]))
            return "Joke has been added";

        return "Joke could not be added for some reason";
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                String.format("%s%s [joke-text]", Strings.commandPrefix, keyword),
                String.format("%s%s \"Y did the Chicken cross the road?\nBecause she wanted to prove that she wasn't a duck.\"", Strings.commandPrefix, keyword),
                "Adds a joke to the joke database"
        );
    }
}
