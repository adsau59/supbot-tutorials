package in.definex.Joke;

import in.definex.Action.Core.SendMessageAction;
import in.definex.Bot;
import in.definex.Scheduler.ReScheduler;
import in.definex.Scheduler.ScheduleTask;

import java.util.ArrayList;


/**
 * ConsoleCommand to run
 *
 * schedule create joke 2018.10.20.09.00.00 JokeST
 *
 */
public class JokeST implements ScheduleTask {

    private ArrayList<String> groupIds;

    public JokeST() {
        groupIds = new ArrayList<>();
    }

    public boolean addIfDoesntExist(String groupID){
        for(String g:groupIds){
            if(groupID.equals(g))
                return false;
        }
        groupIds.add(groupID);
        return true;
    }

    public boolean remove(String groupID){
        return groupIds.remove(groupID);
    }

    @Override
    public void task() {

        String joke = JokeDatabase.GetRandomJoke();
        for(String g: groupIds){
            Bot.getActionManager().add(
                    new SendMessageAction(
                            Bot.getChatGroupsManager().findGroupById(g),
                            "Joker is here!!!\n\n"+joke
                    )
            );
        }

    }

    @Override
    public void taskMissed() {
        for(String g: groupIds){
            Bot.getActionManager().add(
                    new SendMessageAction(
                            Bot.getChatGroupsManager().findGroupById(g),
                            "Joker forgot to arrive today, lets hope they come tomorrow"
                    )
            );
        }


    }

    @Override
    public String getNextDate(String lastScheduleDate) {
        return ReScheduler.AfterDaysSameTime(lastScheduleDate, 1);
    }
}
