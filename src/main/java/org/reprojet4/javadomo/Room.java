package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Room {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();
    boolean check = true;
    String order;

    public JTable Request(Connection co, int orderby){
        switch (orderby){
            case 0:
                if (check == true){
                    order = "room_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "room_name";
                }
                break;
            case 2:
                if (check == true){
                    order = "room_user_id";
                }
                break;
        }
        String request = "SELECT room_id, room_name, room_user_id, room_description " +
                "FROM room " +
                " ORDER BY " + order +" ASC;";
        String[] t = {"id", "name", "user", "description"};
        table = tableAdd.Tab(co, t, request);
        return table;
    }

    public void Update(int id, Connection co, String value1, String value2, String value3) throws SQLException {
        String request = "UPDATE room "+
                "SET room_name = '"+value1+"', room_user_id = '"+value2+"', room_description = '"+value3+"' "+
                "WHERE room_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
    public JTable UpdateAdd(Connection co, int id, String role){
        if (role.equals("admin")){
            String request = "SELECT room_id, room_name, room_user_id, room_description " +
                    "FROM room;";
            String[] t = {"id", "name", "user", "description"};
            table = tableAdd.Tab(co, t, request);
        }else{
            String request = "SELECT room_id, room_name, room_description " +
                    "FROM room " +
                    "WHERE room_user_id = "+id+";";
            String[] t = {"id", "name", "description"};
            table = tableAdd.Tab(co, t, request);
        }
        return table;
    }

    public void Insert(int id, String value1, String value2, Connection co) throws SQLException {
        String request = "INSERT INTO room (room_name, room_user_id, room_description) " +
                "VALUES ('"+value1+"', '"+id+"', '"+value2+"')";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }

    public void Delete(int id, Connection co) throws SQLException {
        String request = "DELETE FROM room WHERE room_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
}
