package in.definex.UrbanDictionary;

import in.definex.Feature.Command;
import in.definex.Feature.Feature;

public class UrbanDictionaryFeature extends Feature {

    public UrbanDictionaryFeature() {
        super("UrbanDictionary", new Command[]{new UDCommand()});
    }

    @Override
    public String getDescription() {
        return "Stay hip with urban dictionary, search definitions of latest slags";
    }
}
