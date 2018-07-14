package in.definex.PinMessages;

import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.String.Strings;

public class DeletePinCommand extends Command {

    private static final String keyword = "delete-pin";

    public DeletePinCommand() {
        super(keyword, 1, Client.Role.Elder);
    }

    @Override
    protected String compute(Client client, String[] strings) {
        //todo elders can delete their own pinned messages but admin and co admin can delete anyones
        int rowid;
        try{
            rowid = Integer.parseInt(strings[0]);
        }catch (NumberFormatException e){
            return "Not a valid rowid";
        }

        Pin pin = PinDatabase.GetPinWithID(rowid);

        if(client.getRole() == Client.Role.Elder &&
                !client.getName().equals(pin.getCreator().getName()))
            return "You are not the author of this pin, You can not delete it.";

        if(!client.getChatGroup().getGroupId().equals(pin.getCreator().getGroupId()))
            return "The pin doesn't belong to this group.";

        if(PinDatabase.DeletePinWithID(rowid))
            return "Pinned Message has been deleted.";

        return "Pin was not deleted due to some error.";
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                String.format("%s%s [message-id]", Strings.commandPrefix, keyword),
                String.format("%s%s [message-id]", Strings.commandPrefix, keyword),
                "Delete your pinned messages"
        );
    }
}
