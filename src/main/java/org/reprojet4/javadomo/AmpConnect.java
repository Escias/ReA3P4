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

    /**
     * SELECT request
     * @param co
     * @param orderby
     * @return
     */
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

    /**
     * UPDATE request with information given in Window Class.
     * @param id
     * @param co
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param selection1
     * @param selection2
     * @throws SQLException
     */
    public void Update(int id, Connection co, String value1, String value2, String value3, String value4, int selection1, int selection2) throws SQLException {
        Integer obj1 = selection2;
        if (obj1.equals(1)){
            stat = "on";
        }else if (obj1.equals(2)){
            stat = "off";
        }else if (obj1.equals(3)){
            stat = "scheduled";
        }
        String request = "Update ampconnect " +
                "SET amp_name = '"+value1+"', amp_room_id = '"+selection1+"', amp_status = '"+stat+"', amp_color = '"+value2+"', amp_time_on = '"+value3+"', amp_time_off = '"+value4+"' " +
                "WHERE amp_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }

    /**
     * Get name of bulb depending of the user's type
     * @param co
     * @param id
     * @param role
     * @return
     */
    public JTable UpdateAdd(Connection co, int id, String role){
        if (role.equals("admin")){
            String request = "SELECT amp_id, amp_name, amp_color, amp_time_on, amp_time_off " +
                    "FROM ampconnect;";
            String[] t = {"id", "name", "color", "activation time", "extinction time"};
            table = tableAdd.Tab(co, t, request);
        }else{
            String request = "SELECT amp_id, amp_name, amp_color, amp_time_on, amp_time_off " +
                    "FROM ampconnect AS A " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = A.amp_room_id " +
                    "WHERE R.room_user_id = "+id+";";
            String[] t = {"id", "name", "color", "activation time", "extinction time"};
            table = tableAdd.Tab(co, t, request);
        }
        return table;
    }
//
    List<String> ls = new ArrayList<>();
    String stat;

    /**
     * INSERT request
     * @param value1
     * @param value2
     * @param selection1
     * @param selection2
     * @param co
     * @throws SQLException
     */
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

    /**
     * Get the name of room
     * @param id
     * @param co
     * @param role
     * @return
     * @throws SQLException
     */
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

    /**
     * DELETE request
     * @param id
     * @param co
     * @throws SQLException
     */
    public void Delete(int id, Connection co) throws SQLException {
        String request = "DELETE FROM ampconnect WHERE amp_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
}
