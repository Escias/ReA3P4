package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ThermoIntel {
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
                    order = "thermo_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "R.room_name";
                }
                break;
            case 2:
                if (check == true){
                    order = "thermo_name";
                }
                break;
            case 3:
                if (check == true){
                    order = "nom_1";
                }
                break;
            case 4:
                if (check == true){
                    order = "nom_2";
                }
                break;
            case 5:
                if (check == true){
                    order = "thermo_temp_target";
                }
                break;
            case 6:
                if (check == true){
                    order = "thermo_status";
                }
                break;
        }
        String request = "SELECT thermo_id, R.room_name, thermo_name, thermo_temp_target, thermo_status, " +
                "(SELECT sensor_name FROM sensor as S WHERE S.sensor_id = T.thermo_id_1) as nom_1, " +
                "(SELECT sensor_name FROM sensor as S WHERE S.sensor_id = T.thermo_id_2) as nom_2 " +
                "FROM thermointel as T " +
                "LEFT JOIN sensor as S " +
                "ON T.thermo_name = S.sensor_name " +
                "LEFT JOIN room as R " +
                "ON R.room_id = T.thermo_room_id " +
                " ORDER BY " + order +" ASC;";
        String[] t = {"id", "room", "name", "temp. target", "status", "sensor 1", "sensor 2"};
        table = tableAdd.Tab(co, t, request);
        return table;
    }

    /**
     * UPDATE request
     * @param id
     * @param co
     * @param value1
     * @param value2
     * @param selection1
     * @param selection2
     * @param selection3
     * @param selection4
     * @throws SQLException
     */
    public void Update(int id, Connection co, String value1, String value2, int selection1, int selection2, int selection3, int selection4) throws SQLException {
        Integer obj1 = selection4;
        if (obj1.equals(1)){
            stat = "inactif";
        }else if (obj1.equals(2)){
            stat = "chaud";
        }else if (obj1.equals(3)){
            stat = "froid";
        }
        String request = "UPDATE thermointel "+
                "SET thermo_room_id = '"+selection1+"', thermo_name = '"+value1+"', thermo_id_1 = '"+selection2+"', thermo_id_2 = '"+selection3+"', thermo_temp_target = '"+value2+"', thermo_status = '"+stat+"' "+
                "WHERE thermo_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }

    /**
     * Get thermo information
     * @param co
     * @param id
     * @param role
     * @return
     */
    public JTable UpdateAdd(Connection co, int id, String role){
        if (role.equals("admin")){
            String request = "SELECT thermo_id, thermo_name, thermo_temp_target " +
                    "FROM thermointel;";
            String[] t = {"id", "name", "temp. target"};
            table = tableAdd.Tab(co, t, request);
        }else{
            String request = "SELECT thermo_id, thermo_name, thermo_temp_target " +
                    "FROM thermointel as T " +
                    "LEFT JOIN sensor as S " +
                    "ON T.thermo_name = S.sensor_name " +
                    "LEFT JOIN room as R " +
                    "ON R.room_id = T.thermo_room_id " +
                    "WHERE R.room_user_id = "+id+";";
            String[] t = {"id", "name", "temp. target"};
            table = tableAdd.Tab(co, t, request);
        }
        return table;
    }

    List<String> ls = new ArrayList<>();
    List<String> ls1 = new ArrayList<>();
    String stat;

    /**
     * INSERT request
     * @param selection1
     * @param selection2
     * @param selection3
     * @param selection4
     * @param value1
     * @param value2
     * @param co
     * @throws SQLException
     */
    public void Insert(int selection1, int selection2, int selection3, int selection4, String value1, String value2, Connection co) throws SQLException {
        Integer obj1 = selection4;
        if (obj1.equals(1)){
            stat = "inactif";
        }else if (obj1.equals(2)){
            stat = "chaud";
        }else if (obj1.equals(3)){
            stat = "froid";
        }
        String request = "INSERT INTO thermointel (thermo_room_id, thermo_name, thermo_id_1, thermo_id_2, thermo_temp_target, thermo_status) " +
                "VALUES ('"+selection1+"', '"+value1+"', '"+selection2+"', '"+selection3+"', '"+value2+"', '"+stat+"')";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }

    /**
     * Get room's name
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
     * Get sensor's name
     * @param id
     * @param co
     * @param role
     * @return
     * @throws SQLException
     */
    public List InsertAdd1(int id, Connection co, String role) throws SQLException {
        if (role.equals("admin")){
            String request = "SELECT sensor_id, sensor_name " +
                    "FROM sensor AS S " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = S.sensor_room_id;";
            Statement stm = co.createStatement();
            ResultSet rslt = stm.executeQuery(request);
            while (rslt.next()){
                ls1.add(rslt.getString(2));
            }
        }else{
            String request = "SELECT sensor_id, sensor_name " +
                    "FROM sensor AS S " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = S.sensor_room_id " +
                    "WHERE R.room_user_id = " + id +";";
            Statement stm = co.createStatement();
            ResultSet rslt = stm.executeQuery(request);
            while (rslt.next()){
                ls1.add(rslt.getString(2));
            }
        }
        return ls1;
    }

    /**
     * DELETE request
     * @param id
     * @param co
     * @throws SQLException
     */
    public void Delete(int id, Connection co) throws SQLException {
        String request = "DELETE FROM thermointel WHERE thermo_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
}
