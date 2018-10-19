package in.definex.RemindMe;

import in.definex.Action.Core.SendMessageAction;
import in.definex.Bot;
import in.definex.ChatSystem.ChatGroup;
import in.definex.Scheduler.ScheduleTask;

public class RemindMeST implements ScheduleTask {

    private String message;
    private String groupId;

    public RemindMeST(String message, String groupId) {
        this.message = message;
        this.groupId = groupId;
    }

    @Override
    public void task() {

        ChatGroup group = Bot.getChatGroupsManager().findGroupById(groupId);

        Bot.getActionManager().add(
                new SendMessageAction(group, "Reminding you!!!\n\n"+message)
        );

    }

    @Override
    public void taskMissed() {
        ChatGroup group = Bot.getChatGroupsManager().findGroupById(groupId);

        Bot.getActionManager().add(
                new SendMessageAction(group, "Forgot to remind you!!!\n\n"+message)
        );
    }

    @Override
    public String getNextDate(String lastScheduleDate) {
        return null;
    }
}
