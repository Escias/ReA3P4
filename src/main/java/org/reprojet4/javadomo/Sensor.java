package org.reprojet4.javadomo;

public class Sensor {

    public void Request(int id){
        String request = "SELECT sensor_id, sensor_name, R.room_name, sensor_status, sensor_interval, sensor_temp_min, sensor_temp_max " +
                "FROM sensor AS S " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = S.sensor_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY sensor_id ASC;";
        String[] t = {"id", "nom", "salle", "status", "interval (s)", "temp. min", "temp. max"};
    }

    public void Update(int id){

    }

    public void Insert(int id){

    }

    public void Delete(int id){

    }
}
