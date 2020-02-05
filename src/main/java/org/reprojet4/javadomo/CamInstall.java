package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class CamInstall {
    TableAdd tableAdd = new TableAdd();
    JScrollPane table = new JScrollPane();
    boolean check = true;
    String order;

    public JScrollPane Request(Connection co, int orderby){
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
