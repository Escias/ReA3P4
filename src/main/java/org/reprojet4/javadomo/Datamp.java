package org.reprojet4.javadomo;

public class Datamp {

    public void Request(int id){
        String request = "SELECT datamp_id, A.amp_name, datamp_action, datamp_datetime " +
                "FROM datamp AS D " +
                "LEFT JOIN ampconnect AS A " +
                "ON A.amp_id = D.datamp_amp_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                "WHERE room_user_id = " + id +
                " ORDER BY datamp_id ASC;";
        String[] t = {"name", "description"};
    }

    public void Update(int id){

    }

    public void Insert(int id){

    }

    public void Delete(int id){

    }
}
