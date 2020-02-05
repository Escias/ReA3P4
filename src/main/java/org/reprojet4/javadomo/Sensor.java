package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class Sensor {
    TableAdd tableAdd = new TableAdd();
    JScrollPane table = new JScrollPane();
    boolean check = true;
    String order;

    public JScrollPane Request(Connection co, int orderby){
        switch (orderby){
            case 0:
                if (check == true){
                    order = "sensor_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "sensor_name";
                }
                break;
            case 2:
                if (check == true){
                    order = "R.room_name";
                }
                break;
            case 3:
                if (check == true){
                    order = "sensor_status";
                }
                break;
            case 4:
                if (check == true){
                    order = "sensor_interval";
                }
                break;
        }
        String request = "SELECT sensor_id, sensor_name, R.room_name, sensor_status, sensor_interval, sensor_temp_min, sensor_temp_max " +
                "FROM sensor AS S " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = S.sensor_room_id " +
                " ORDER BY " + order +" ASC;";
        String[] t = {"id", "name", "room", "status", "interval (s)", "temp. min", "temp. max"};
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
