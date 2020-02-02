package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class AmpConnect {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();

    public JTable Request(int id, Connection co){
        table.removeAll();
        table.revalidate();
        table.repaint();
        String request = "SELECT amp_name, R.room_name, amp_status, amp_color, amp_time_on, amp_time_off " +
                "FROM ampconnect AS A " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY amp_name ASC;";
        String[] t = {"name", "salle", "status", "couleur", "heure d'activation", "heure d'extinction"};
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
