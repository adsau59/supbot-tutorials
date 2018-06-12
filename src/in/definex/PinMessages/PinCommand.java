package in.definex.PinMessages;

import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.String.Strings;

public class PinCommand extends Command {

    private static final String keyword = "pin";

    public PinCommand() {
        super(keyword, 1, Client.Role.Elder);
    }

    @Override
    protected String compute(Client client, String[] strings) {

        if(PinDatabase.CreatePin(client, strings[0]))
            return "Your message has been pinned.";

        return "Message was not pinned due to some error.";
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                String.format("%s%s [message]", Strings.commandPrefix, keyword),
                String.format("%s%s \"Supbot is Awesome\"", Strings.commandPrefix, keyword),
                "Pin messages to check it in the future."
        );
    }
}
