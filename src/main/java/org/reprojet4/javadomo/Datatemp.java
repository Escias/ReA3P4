package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class Datatemp {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();
    boolean check = true;
    String order;

    public JTable Request(Connection co, int orderby){
        switch (orderby){
            case 0:
                if (check == true){
                    order = "datatemp_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "S.sensor_id";
                }
                break;
            case 2:
                if (check == true){
                    order = "datatemp_temp";
                }
                break;
            case 3:
                if (check == true){
                    order = "datatemp_time";
                }
                break;
        }
        String request = "SELECT datatemp_id, S.sensor_name, datatemp_temp, datatemp_time " +
                "FROM datatemp AS D " +
                "LEFT JOIN sensor AS S " +
                "ON S.sensor_id = D.datatemp_sensor_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = S.sensor_room_id " +
                " ORDER BY " + order +" ASC;";
        String[] t = {"id", "capteur", "température", "date et heure"};
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
