package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class Food {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();

    public JTable Request(int id, Connection co, String role){
        String request = "SELECT food_id, R.room_name, food_name, food_perempt, food_quantity " +
                "FROM food AS F " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = F.food_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY food_id ASC;";
        String[] t = {"id", "salle", "nom", "péremption", "quantité"};
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
