package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        table = tableAdd.Tab(co, t, request);
        return table;
    }
//
    public void Update(int id){

    }
//
    List<String> ls = new ArrayList<>();
    String stat;

    public void Insert(String value1, String value2, int selection1, int selection2, Connection co) throws SQLException {
        Integer obj1 = selection2;
        if (obj1.equals(1)){
            stat = "on";
        }else if (obj1.equals(2)){
            stat = "off";
        }else if (obj1.equals(3)){
            stat = "scheduled";
        }
        String request = "INSERT INTO ampconnect (amp_name, amp_room_id, amp_status, amp_color, amp_time_on, amp_time_off) " +
                    "VALUES ('"+value1+"', '"+selection1+"', '"+stat+"', '"+value2+"', NOW(), NOW() + INTERVAL 5 HOUR)";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
    public List InsertAdd(int id, Connection co, String role) throws SQLException {
        System.out.println(role);
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
//
    JTable tab = new JTable();

    public void Delete(int id, Connection co) throws SQLException {
        String request = "DELETE FROM ampconnect WHERE amp_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
    public JTable DeleteAdd(int id, String role, Connection co){
        if (role.equals("admin")) {
            String request = "SELECT amp_id, amp_name " +
                    "FROM ampconnect AS A " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = A.amp_room_id;";
            String[] t = {"id", "name"};
            tab = tableAdd.Tab(co, t, request);
        } else {
            String request = "SELECT amp_id, amp_name " +
                    "FROM ampconnect AS A " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = A.amp_room_id " +
                    "WHERE R.room_user_id = " + id + ";";
            String[] t = {"id", "name"};
            tab = tableAdd.Tab(co, t, request);
        }
        return tab;
    }
}
