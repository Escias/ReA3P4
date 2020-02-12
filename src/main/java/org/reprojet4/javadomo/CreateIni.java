package org.reprojet4.javadomo;

import org.ini4j.Wini;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class CreateIni {
    File file = new File("log.ini");

    public void CreateIni(JTextField username, JPasswordField password, JCheckBox check){
        try {
            if(check.isSelected()){
                file.createNewFile();
                Wini ini = new Wini(file);
                ini.put("Identifiant", "mail", username.getText());
                ini.put("Identifiant", "password", password.getText());
                ini.store();
            }
            else{
                file.delete();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void Check(JTextField username, JPasswordField password) {
        System.out.println("file has been created");
        if (file.exists()) {
            try {
                Wini ini = new Wini(file);
                String stringMail = ini.get("Identifiant", "mail");
                String stringPassword = ini.get("Identifiant", "password");
                username.setText(stringMail);
                password.setText(stringPassword);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
