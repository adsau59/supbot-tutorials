package in.definex.Math;

import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


/**
 * Created by adam_ on 01-12-2017.
 */
public class SolveCommand extends Command {

    public static final String name = "solve";

    SolveCommand() {
        super("solve", -1, Client.Role.Member);
    }

    @Override
    protected String compute(Client client, String[] args) {

        //3 + ( 2 - 3 ) / 5

        if(args.length == 0)
            return invalidArgumentsRespose();

        String expr = "";
        for(String s: args){
            expr += s;
        }

        try {
            return  expr+"="+(new ScriptEngineManager().getEngineByName("JavaScript").eval(expr)).toString();
        } catch (ScriptException e) {
            return invalidArgumentsRespose();
        }

    }

    @Override
    public Command.Helper getHelper() {
        return new Helper(
                ";;solve [equation]",
                ";;solve 8+3/(9+1)",
                "Solves equation you give it to."
        );
    }


}
