package in.definex.PinMessages;

import in.definex.ChatSystem.ChatGroup;
import in.definex.ChatSystem.Client;
import in.definex.Console.Log;
import in.definex.Database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PinDatabase extends Database {

    public PinDatabase() {
        super(1, "pin");

        addVersionChanger(new VersionChanger(1) {
            @Override
            public void upTo(Connection connection) throws SQLException {
                connection.createStatement().execute("CREATE TABLE pin (client_name VARCHAR(30), group_id VARCHAR(30), pin_message TEXT)");
            }

            @Override
            public void downFrom(Connection connection) throws SQLException {
                connection.createStatement().execute("DROP TABLE pin");
            }
        });

    }

    public static boolean CreatePin(Client creator, String message){

        try(Connection conn = connect();
        PreparedStatement pstm = conn.prepareStatement("INSERT INTO pin VALUES(?,?,?)")){

            pstm.setString(1, creator.getName());
            pstm.setString(2, creator.getGroupId());
            pstm.setString(3, message);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }


    public static Pin[] GetAllPinsOfGroup(ChatGroup chatGroup){

        List<Pin> pins = new ArrayList<>();

        try(Connection conn = connect();
        PreparedStatement pstm = conn.prepareStatement("SELECT * FROM pin WHERE group_id=?")){

            pstm.setString(1, chatGroup.getGroupId());
            ResultSet rs = pstm.executeQuery();

            while(rs.next()){

                Pin pin = new Pin(
                        //rs.getInt("rowid"),
                        rs.getRow(),
                        Client.getClient(
                                rs.getString("client_name"),
                                chatGroup.getGroupId()
                        ),
                        rs.getString("pin_message")
                );

                pins.add(pin);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pins.toArray(new Pin[0]);

    }

    public static boolean DeletePinWithID(int rowid){

        try(Connection conn = connect();
        PreparedStatement pstm = conn.prepareStatement("DELETE FROM pin WHERE rowid=?")){

            pstm.setInt(1,rowid);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public static Pin GetPinWithID(int rowid){

        try(Connection conn = connect();
        PreparedStatement pstm = conn.prepareStatement("SELECT * FROM pin WHERE rowid=?")){

            pstm.setInt(1,rowid);
            ResultSet rs = pstm.executeQuery();

            if(!rs.isBeforeFirst()){
                Log.d("PinDatabase","pin not found");
                return null;
            }

            return new Pin(
                    rowid,
                    Client.getClient(rs.getString("client_name"), rs.getString("group_id")),
                    rs.getString("pin_message")
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
