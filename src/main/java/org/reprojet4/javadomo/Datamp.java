package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class Datamp {
    TableAdd tableAdd = new TableAdd();
    JScrollPane table = new JScrollPane();
    boolean check = true;
    String order;

    public JScrollPane Request(Connection co, int orderby){
        switch (orderby){
            case 0:
                if (check == true){
                    order = "datamp_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "A.amp_name";
                }
                break;
            case 2:
                if (check == true){
                    order = "datamp_action";
                }
                break;
            case 3:
                if (check == true){
                    order = "datamp_datetime";
                }
                break;
        }
        String request = "SELECT datamp_id, A.amp_name, datamp_action, datamp_datetime " +
                "FROM datamp AS D " +
                "LEFT JOIN ampconnect AS A " +
                "ON A.amp_id = D.datamp_amp_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                " ORDER BY "+ order +" ASC;";
        String[] t = {"id", "bulb", "action", "date and hour"};
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
