package in.definex.Joke;

import in.definex.Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


/**
 * Database class for Joke
 * just has one column, joke_text
 *
 * Made in Exercise of Ep.3 Database
 */
public class JokeDatabase extends Database {

    public JokeDatabase() {
        super(1, "Joke");

        /**
         * Version changer for v1
         */
        addVersionChanger(new VersionChanger(1) {

            /**
             * called when database has to be upgraded to version 1 from 0
             * Create a take with just joke_text column
             */
            @Override
            public void upTo(Connection connection) throws SQLException {
                connection.createStatement().execute("CREATE TABLE joke (joke_text TEXT)");
            }

            /**
             * Called when database has to be downgraded from version 1 to 0
             * Drop the table
             */
            @Override
            public void downFrom(Connection connection) throws SQLException {
                connection.createStatement().execute("DROP TABLE joke");
            }
        });
    }


    /**
     * Inserts a joke to the database.
     * @param joke joke text to be inserted
     * @return true insert was successful
     */
    public static boolean AddJoke(String joke){

        try(Connection conn= connect();
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO joke VALUES(?)")){

            pstm.setString(1, joke);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * Returns a randome joke from the database.
     * @return Random joke string
     */
    public static String GetRandomJoke(){

        try(Connection conn= connect()){

            ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) AS \"count\" FROM joke");

            int count = rs.getInt("count");
            if(count == 0)
                return null;

            int rand = new Random().nextInt(count)+1;

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM joke WHERE rowid=?");
            pstm.setInt(1, rand);
            ResultSet rs2 = pstm.executeQuery();

            return rs2.getString("joke_text");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
