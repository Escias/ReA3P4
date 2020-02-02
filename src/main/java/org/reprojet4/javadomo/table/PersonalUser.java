package org.reprojet4.javadomo.table;

public class PersonalUser {

    public void Request(int id){
        String request = "SELECT user_lastname, user_firstname, user_mail, user_phone, user_adress, user_ZIP, user_type " +
                "FROM personal_user " +
                "WHERE user_id = "+id+
                " ORDER BY user_lastname ASC;";
        String[] t = {"nom", "prénom", "mail", "téléphone", "adresse", "ZIP", "type"};
    }

    public void Update(int id){

    }

    public void Insert(int id){

    }

    public void Delete(int id){

    }
}
