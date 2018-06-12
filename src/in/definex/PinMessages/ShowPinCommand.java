package in.definex.PinMessages;

import in.definex.Action.Core.SendMessageAction;
import in.definex.Bot;
import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.String.Strings;

public class ShowPinCommand extends Command {

    private static final String keyword = "show-pins";

    public ShowPinCommand() {
        super(keyword, 0, Client.Role.Member);
    }

    private static final String format = "" +
            "Pin id: %s\n" +
            "Author: %s\n" +
            "\n" +
            "%s";

    @Override
    protected String compute(Client client, String[] strings) {

        Pin[] pins = PinDatabase.GetAllPinsOfGroup(client.getChatGroup());

        for(Pin p:pins){

            Bot.getActionManager().add(
                    new SendMessageAction(
                            client.getChatGroup(),
                            String.format(format, p.getPinId(), p.getCreator().getFaceName(), p.getMessage())
                    )
            );

        }

        if(pins.length == 0)
            return "No messages have been pinned yet";

        return "Showing all the pins.";
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                String.format("%s%s", Strings.commandPrefix, keyword),
                String.format("%s%s", Strings.commandPrefix, keyword),
                "Shows the pinned messages"
        );
    }
}
