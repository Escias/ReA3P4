package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Room {
    TableAdd tableAdd = new TableAdd();
    JScrollPane table = new JScrollPane();
    boolean check = true;
    String order;

    public JScrollPane Request(Connection co, int orderby){
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
        String[] t = {"name", "description"};
        table = tableAdd.Table(co, t, request);
        return table;
    }

    public void Update(int id){

    }

    public void Insert(int id, String value1, String value2, Connection co) throws SQLException {
        String request = "INSERT INTO room (room_name, room_user_id, room_description)" +
                "VALUES ('"+value1+"', '"+id+"', '"+value2+"')";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }

    public void Delete(int id){

    }
}
