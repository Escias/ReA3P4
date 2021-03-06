package org.reprojet4.javadomo;

import jdk.nashorn.internal.runtime.JSType;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Datatemp {
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
        table = tableAdd.Tab(co, t, request);
        return table;
    }

    List<String> ls = new ArrayList<>();

    /**
     * INSERT request
     * @param selection1
     * @param value1
     * @param co
     * @throws SQLException
     */
    public void Insert(int selection1, String value1, Connection co) throws SQLException {
        String request = "INSERT INTO datatemp (datatemp_sensor_id, datatemp_temp, datatemp_time) " +
                "VALUES ('"+selection1+"', '"+value1+"', NOW())";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }

    /**
     * Get sensor's name
     * @param id
     * @param co
     * @param role
     * @return
     * @throws SQLException
     */
    public List InsertAdd(int id, Connection co, String role) throws SQLException {
        if (role.equals("admin")) {
            String request = "SELECT sensor_id, sensor_name " +
                    "FROM sensor AS S " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = S.sensor_room_id;";
            Statement stm = co.createStatement();
            ResultSet rslt = stm.executeQuery(request);
            while (rslt.next()) {
                ls.add(rslt.getString(2));
            }
        } else {
            String request = "SELECT sensor_id, sensor_name " +
                    "FROM sensor AS S " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = S.sensor_room_id " +
                    "WHERE R.room_user_id = " + id +
                    " ORDER BY sensor_id ASC;";
            Statement stm = co.createStatement();
            ResultSet rslt = stm.executeQuery(request);
            while (rslt.next()) {
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
        String request = "DELETE FROM datatemp WHERE datatemp_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
}
