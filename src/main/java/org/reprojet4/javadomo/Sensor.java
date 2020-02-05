package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class Sensor {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();

    public JTable Request(Connection co, int orderby){
        String request = "SELECT sensor_id, sensor_name, R.room_name, sensor_status, sensor_interval, sensor_temp_min, sensor_temp_max " +
                "FROM sensor AS S " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = S.sensor_room_id " +
                " ORDER BY sensor_id ASC;";
        String[] t = {"id", "nom", "salle", "status", "interval (s)", "temp. min", "temp. max"};
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
