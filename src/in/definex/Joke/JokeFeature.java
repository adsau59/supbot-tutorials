package in.definex.Joke;

import in.definex.Feature.Command;
import in.definex.Feature.Feature;

public class JokeFeature extends Feature {

    public JokeFeature() {
        super("Joke", new Command[]{new TellAJokeCommand()});
    }

    @Override
    public String getDescription() {
        return "A very funny feature, tells jokes.";
    }
}
