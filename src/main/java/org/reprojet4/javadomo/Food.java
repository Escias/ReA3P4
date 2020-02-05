package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class Food {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();
    boolean check = true;
    String order;

    public JTable Request(Connection co, int orderby){
        switch (orderby){
            case 0:
                if (check == true){
                    order = "food_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "R.room_name";
                }
                break;
            case 2:
                if (check == true){
                    order = "food_name";
                }
                break;
            case 3:
                if (check == true){
                    order = "food_perempt";
                }
                break;
            case 4:
                if (check == true){
                    order = "food_quantity";
                }
                break;
        }
        String request = "SELECT food_id, R.room_name, food_name, food_perempt, food_quantity " +
                "FROM food AS F " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = F.food_room_id " +
                " ORDER BY " + order +" ASC;";
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
