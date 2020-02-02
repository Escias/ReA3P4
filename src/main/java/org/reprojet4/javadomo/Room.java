package org.reprojet4.javadomo;

public class Room {

    public void Request(int id){
        String request = "SELECT room_name, room_description " +
                "FROM room " +
                "WHERE room_user_id = " + id +
                " ORDER BY room_name ASC;";
        String[] t = {"name", "description"};
    }

    public void Update(int id){

    }

    public void Insert(int id){

    }

    public void Delete(int id){

    }
}
