package in.definex.PinMessages;

import in.definex.Feature.Command;
import in.definex.Feature.Feature;

public class PinFeature extends Feature {
    public PinFeature() {
        super("Pin", new Command[]{
                new PinCommand(),
                new ShowPinCommand(),
                new DeletePinCommand()
        });
    }

    @Override
    public String getDescription() {
        return "Pin messages to refer them later.";
    }
}
