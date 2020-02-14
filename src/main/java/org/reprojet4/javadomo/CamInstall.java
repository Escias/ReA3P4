package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CamInstall {
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
                    order = "cam_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "cam_name";
                }
                break;
            case 2:
                if (check == true){
                    order = "R.room_name";
                }
                break;
            case 3:
                if (check == true){
                    order = "cam_status";
                }
                break;
            case 4:
                if (check == true){
                    order = "cam_dist";
                }
                break;
            case 5:
                if (check == true){
                    order = "cam_time_begin";
                }
                break;
            case 6:
                if (check == true){
                    order = "cam_time_end";
                }
                break;
        }
        String request = "SELECT cam_id, cam_name, R.room_name, cam_status, cam_dist, cam_time_begin, cam_time_end " +
                "FROM caminstall AS C " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = C.cam_room_id " +
                " ORDER BY "+ order +" ASC;";
        String[] t = {"id", "name", "room", "status", "distance", "begin", "end"};
        table = tableAdd.Tab(co, t, request);
        return table;
    }

    /**
     * UPDATE request
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
        }
        String request = "Update caminstall " +
                "SET cam_name = '"+value1+"', cam_room_id = '"+selection1+"', cam_status = '"+stat+"', cam_dist = '"+value2+"', cam_time_begin = '"+value3+"', cam_time_end = '"+value4+"' " +
                "WHERE cam_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }

    /**
     * Get camera's name
     * @param co
     * @param id
     * @param role
     * @return
     */
    public JTable UpdateAdd(Connection co, int id, String role){
        if (role.equals("admin")){
            String request = "SELECT cam_id, cam_name, cam_dist, cam_time_begin, cam_time_end " +
                    "FROM caminstall AS C;";
            String[] t = {"id", "name", "distance", "begin", "end"};
            table = tableAdd.Tab(co, t, request);
        }else{
            String request = "SELECT cam_id, cam_name, R.room_name, cam_status, cam_dist, cam_time_begin, cam_time_end " +
                    "FROM caminstall AS C " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = C.cam_room_id " +
                    "WHERE R.room_user_id = "+id+";";
            String[] t = {"id", "name", "distance", "begin", "end"};
            table = tableAdd.Tab(co, t, request);
        }
        return table;
    }

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
        }
        String request = "INSERT INTO caminstall (cam_name, cam_room_id, cam_status, cam_dist, cam_time_begin, cam_time_end) " +
                "VALUES ('"+value1+"', '"+selection1+"', '"+stat+"', '"+value2+"', NOW(), NOW() + INTERVAL 5 DAY)";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }

    /**
     * Get room name
     * @param id
     * @param co
     * @param role
     * @return
     * @throws SQLException
     */
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

    /**
     * DELETE request
     * @param id
     * @param co
     * @throws SQLException
     */
    public void Delete(int id, Connection co) throws SQLException {
        String request = "DELETE FROM caminstall WHERE cam_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
}
