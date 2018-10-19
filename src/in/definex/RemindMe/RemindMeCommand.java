package in.definex.RemindMe;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import in.definex.Bot;
import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.Scheduler.Schedule;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.List;

public class RemindMeCommand extends Command {

    public RemindMeCommand() {
        super("remindme", 2, Client.Role.Member);
    }

    @Override
    protected String compute(Client client, String[] args) {

        Parser parser = new Parser();
        List<DateGroup> groups = parser.parse(args[0]);

        int i= 0;

        for(DateGroup group:groups) {
            List<Date> dates = group.getDates();

            for (Date d:dates){

                Schedule schedule = new Schedule(
                        new RemindMeST(args[1], client.getGroupId()),
                        Schedule.DateFormat.format(d)
                );

                Bot.getScheduleManager().add(
                        "RemindMe"+RandomStringUtils.randomAlphabetic(5),
                        schedule
                );

                i++;

            }
        }

        return String.format("Created %d reminders", i);
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                ";;remindme <date> <message>",
                ";;remindme \"tomorrow morning\" \"Do the work!!!\"",
                "Create reminders"

        );
    }
}
