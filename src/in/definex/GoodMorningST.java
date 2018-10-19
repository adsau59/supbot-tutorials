package in.definex;

import in.definex.Action.Core.SendMessageAction;
import in.definex.ChatSystem.ChatGroup;
import in.definex.Scheduler.ReScheduler;
import in.definex.Scheduler.ScheduleTask;

public class GoodMorningST implements ScheduleTask {

    private String groupId;

    public GoodMorningST(String groupId){
        this.groupId = groupId;
    }

    @Override
    public void task() {
        ChatGroup chatGroup = Bot.getChatGroupsManager().findGroupById(groupId);
        Bot.getActionManager().add(
                new SendMessageAction(chatGroup,"Good Morning!!!")
        );
    }

    @Override
    public void taskMissed() {
        ChatGroup chatGroup = Bot.getChatGroupsManager().findGroupById(groupId);
        Bot.getActionManager().add(
                new SendMessageAction(chatGroup,"Sorry, I was sleeping forgot to say good morning.")
        );
    }

    @Override
    public String getNextDate(String lastScheduleDate) {
        return ReScheduler.AfterDaysSameTime(lastScheduleDate, 1);
    }
}
