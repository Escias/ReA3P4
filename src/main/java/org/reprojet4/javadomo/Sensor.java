package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Sensor {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();
    boolean check = true;
    String order;

    public JTable Request(Connection co, int orderby){
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
        table = tableAdd.Tab(co, t, request);
        return table;
    }

    public void Update(int id, Connection co, String value1, String value2, String value3, String value4, int selection1, int selection2) throws SQLException {
        Integer obj1 = selection2;
        if (obj1.equals(1)){
            stat = "on";
        }else if (obj1.equals(2)){
            stat = "off";
        }
        String request = "UPDATE sensor " +
                "SET sensor_name = '"+value1+"', sensor_room_id = '"+selection1+"', sensor_status = '"+stat+"', sensor_interval = '"+value2+"', sensor_temp_min = '"+value3+"', sensor_temp_max = '"+value4+"' " +
                "WHERE sensor_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
    public JTable UpdateAdd(Connection co, int id, String role){
        if (role.equals("admin")){
            String request = "SELECT sensor_id, sensor_name, sensor_interval, sensor_temp_min, sensor_temp_max " +
                    "FROM sensor;";
            String[] t = {"id", "name", "interval (s)", "temp. min", "temp. max"};
            table = tableAdd.Tab(co, t, request);
        }else{
            String request = "SELECT sensor_id, sensor_name, sensor_interval, sensor_temp_min, sensor_temp_max " +
                    "FROM sensor AS S " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = S.sensor_room_id " +
                    "WHERE R.room_user_id = "+id+";";
            String[] t = {"id", "name", "interval (s)", "temp. min", "temp. max"};
            table = tableAdd.Tab(co, t, request);
        }
        return table;
    }

    List<String> ls = new ArrayList<>();
    String stat;

    public void Insert(String value1, String value2, String value3, String value4, int selection1, int selection2, Connection co) throws SQLException {
        Integer obj1 = selection2;
        if (obj1.equals(1)){
            stat = "on";
        }else if (obj1.equals(2)){
            stat = "off";
        }
        String request = "INSERT INTO sensor (sensor_name, sensor_room_id, sensor_status, sensor_interval, sensor_temp_min, sensor_temp_max) " +
                "VALUES ('"+value1+"', '"+selection1+"', '"+stat+"', '"+value2+"', '"+value3+"', '"+value4+"')";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
    public List InsertAdd(int id, Connection co, String role) throws SQLException {
        if (role.equals("admin")){
            String request = "SELECT room_id, room_name " +
                    "FROM room;";
            Statement stm = co.createStatement();
            ResultSet rslt = stm.executeQuery(request);
            while (rslt.next()){
                ls.add(rslt.getString(2));
            }
        }else{
            String request = "SELECT room_id, room_name " +
                    "FROM room " +
                    "WHERE room_user_id = " + id + ";";
            Statement stm = co.createStatement();
            ResultSet rslt = stm.executeQuery(request);
            while (rslt.next()){
                ls.add(rslt.getString(2));
            }
        }
        return ls;
    }

    public void Delete(int id, Connection co) throws SQLException {
        String request = "DELETE FROM sensor WHERE sensor_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
}
