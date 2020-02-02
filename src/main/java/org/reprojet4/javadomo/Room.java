package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class Room {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();

    public JTable Request(int id, Connection co){
        String request = "SELECT room_name, room_description " +
                "FROM room " +
                "WHERE room_user_id = " + id +
                " ORDER BY room_name ASC;";
        String[] t = {"name", "description"};
        table = tableAdd.Table(co, t, request);
        return table;
    }

    public void Update(int id){

    }

    public void Insert(int id){

    }

    public void Delete(int id){

    }
}
