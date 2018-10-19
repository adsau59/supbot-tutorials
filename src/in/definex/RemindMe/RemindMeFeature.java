package in.definex.RemindMe;

import in.definex.Feature.Command;
import in.definex.Feature.Feature;

public class RemindMeFeature extends Feature {

    public RemindMeFeature() {
        super(
                "RemindMe",
                new Command[]{new RemindMeCommand()});
    }

    @Override
    public String getDescription() {
        return "Create Reminders";
    }
}
