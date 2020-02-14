package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Food {
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
                    order = "food_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "R.room_name";
                }
                break;
            case 2:
                if (check == true){
                    order = "food_name";
                }
                break;
            case 3:
                if (check == true){
                    order = "food_perempt";
                }
                break;
            case 4:
                if (check == true){
                    order = "food_quantity";
                }
                break;
        }
        String request = "SELECT food_id, R.room_name, food_name, food_perempt, food_quantity " +
                "FROM food AS F " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = F.food_room_id " +
                " ORDER BY " + order +" ASC;";
        String[] t = {"id", "salle", "nom", "péremption", "quantité"};
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
     * @param selection1
     * @throws SQLException
     */
    public void Update(int id, Connection co, String value1, String value2, String value3, int selection1) throws SQLException {
        String request = "Update food " +
                "SET food_room_id = '"+selection1+"', food_name = '"+value1+"', food_perempt = '"+value2+"', food_quantity = '"+value3+"' " +
                "WHERE food_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
    /**
     * Get food information.
     * @param co
     * @param id
     * @param role
     * @return
     */
    public JTable UpdateAdd(Connection co, int id, String role){
        if (role.equals("admin")){
            String request = "SELECT food_id, food_name, food_perempt, food_quantity " +
                    "FROM food;";
            String[] t = {"id", "name", "péremption", "quantité"};
            table = tableAdd.Tab(co, t, request);
        }else{
            String request = "SELECT food_id, food_name, food_perempt, food_quantity " +
                    "FROM food AS F " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = F.food_room_id " +
                    "WHERE R.room_user_id = "+id+";";
            String[] t = {"id", "name", "péremption", "quantité"};
            table = tableAdd.Tab(co, t, request);
        }
        return table;
    }

    List<String> ls = new ArrayList<>();

    /**
     * INSERT request
     * @param selection1
     * @param value1
     * @param value2
     * @param value3
     * @param co
     * @throws SQLException
     */
    public void Insert(int selection1, String value1, String value2, String value3, Connection co) throws SQLException {
        String request = "INSERT INTO food (food_room_id, food_name, food_perempt, food_quantity, food_perempt_open, food_open) " +
                "VALUES ('"+selection1+"', '"+value1+"', '"+value2+"', '"+value3+"', NOW() + INTERVAL 30 DAY, NOW())";
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

    /**
     * DELETE request
     * @param id
     * @param co
     * @throws SQLException
     */
    public void Delete(int id, Connection co) throws SQLException {
        String request = "DELETE FROM food WHERE food_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
}
