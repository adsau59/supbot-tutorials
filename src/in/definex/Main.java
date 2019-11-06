package in.definex;

public class Main {

    public static void main(String[] args) {
        //Creating the looper and sending code to create components and initializing it
        Looper looper = new Looper(

                new Looper.ExtraLooperFunctions() {
                    @Override
                    public void addThingsInBot() {
                        Bot.getChatProcessorManager().add(
                                new ProfanityWarnCP()
                        );
                    }

                    @Override
                    public void moreInits() {
                        //initialize things here
                    }
                }

        );
        looper.setChromeProfileLoc("C:\\Work\\Projects\\supbot_tuts\\null");

        //use this method to set your chrome profile
        //looper.setChromeProfileLoc("D:\\Extra\\ChromeProfile");

        //Run the code to initialize components and start the bot.
        //Bot Services can only be used after looper.start()
        looper.start();



        //Wait for the bot to quit.
        looper.join();


    }


}
