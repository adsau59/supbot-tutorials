package in.definex.Joke;

import in.definex.Feature.Command;
import in.definex.Feature.Feature;

/**
 * JokeFeature Class
 *
 * Made in Exercise of Ep.2 Command and Feature
 */
public class JokeFeature extends Feature {

    /**
     * Joke feature has 2 commands, TellAJokeCommand and AddAJokeCommand.
     */
    public JokeFeature() {
        super("Joke", new Command[]{new TellAJokeCommand(), new AddAJokeCommand(), new DailyJokeCommand()});
    }

    /**
     * @return Description of the Feature.
     */
    @Override
    public String getDescription() {
        return "A very funny feature, tells jokes.";
    }
}
