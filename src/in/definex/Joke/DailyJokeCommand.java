package in.definex.Joke;

import in.definex.Bot;
import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.Scheduler.Schedule;

public class DailyJokeCommand extends Command {

    public DailyJokeCommand() {
        super("dailyjoke", 1, Client.Role.CoAdmin);
    }

    @Override
    protected String compute(Client client, String[] args) {

        try{
            Schedule jokeSchedule = Bot.getScheduleManager().get("joke");
            JokeST jokeST = (JokeST)jokeSchedule.getScheduleTask();
            String r = "";
            switch (args[0]) {
                case "on":
                    r = jokeST.addIfDoesntExist(client.getGroupId())
                            ? "Added group in daily joke list successfully" : "Group is already in daily joke list";
                    Bot.getScheduleManager().notifyDBUpdate(jokeSchedule);
                    return r;

                case "off":
                    r =  jokeST.remove(client.getGroupId())
                            ? "Group removed from daily joke list successfully" : "Group not in daily joke list";
                    Bot.getScheduleManager().notifyDBUpdate(jokeSchedule);
                    return r;


                default:
                    return "Incorrect argument";
            }

        }catch (NullPointerException e){
            return "Schedule hasn't initialized the scheduler yet, notify SuperAdmin about this problem";
        }

    }

    @Override
    public Helper getHelper() {
        return new Helper(
                ";;dailyjoke <on|off>",
                ";;dailyjoke on",
                "Send daily jokes in the group"
        );
    }
}
