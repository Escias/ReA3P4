package org.reprojet4.javadomo.table;

public class CamInstall {

    public void Request(int id){
        String request = "SELECT cam_name, R.room_name, cam_status, cam_dist, cam_time_begin, cam_time_end " +
                "FROM caminstall AS C " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = C.cam_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY cam_name ASC;";
        String[] t = {"name", "salle", "status", "distance", "début", "fin"};
    }

    public void Update(int id){

    }

    public void Insert(int id){

    }

    public void Delete(int id){

    }
}
