package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class CamInstall {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();

    public JTable Request(int id, Connection co, String role){
        String request = "SELECT cam_name, R.room_name, cam_status, cam_dist, cam_time_begin, cam_time_end " +
                "FROM caminstall AS C " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = C.cam_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY cam_name ASC;";
        String[] t = {"name", "salle", "status", "distance", "début", "fin"};
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
