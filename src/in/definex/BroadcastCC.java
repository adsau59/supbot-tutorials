package in.definex;

import in.definex.Action.Core.SendMessageAction;
import in.definex.ChatSystem.ChatGroup;
import in.definex.Console.ConsoleCommand;

public class BroadcastCC extends ConsoleCommand {

    public BroadcastCC() {
        super("broadcast", 1);
    }

    @Override
    public String compute(String[] args) {


        for(ChatGroup chatGroup: Bot.getChatGroupsManager().getChatGroupList()){
            Bot.getActionManager().add(
                    new SendMessageAction(
                            chatGroup,
                            args[0]
                    )
            );
        }

        return "Broadcast initialized";
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                "broadcast <message>",
                "broadcast \"Bot is going under maintenance in 5 mins\"",
                "Send message to all the groups using 1 command."
        );
    }
}
