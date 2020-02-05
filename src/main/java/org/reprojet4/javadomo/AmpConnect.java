package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class AmpConnect {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();
    boolean check = true;
    String order;

    public JTable Request(Connection co, int orderby){
        switch (orderby){
            case 0:
                if (check == true){
                    order = "amp_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "amp_name";
                }
                break;
            case 2:
                if (check == true){
                    order = "R.room_name";
                }
                break;
            case 3:
                if (check == true){
                    order = "amp_status";
                }
                break;
            case 4:
                if (check == true){
                    order = "amp_color";
                }
                break;
            case 5:
                if (check == true){
                    order = "amp_time_on";
                }
                break;
            case 6:
                if (check == true){
                    order = "amp_time_off";
                }
                break;
        }
        String request = "SELECT amp_id, amp_name, R.room_name, amp_status, amp_color, amp_time_on, amp_time_off " +
                "FROM ampconnect AS A " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                " ORDER BY "+ order +" ASC;";
        String[] t = {"id", "name", "room", "status", "color", "activation time", "extinction time"};
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
