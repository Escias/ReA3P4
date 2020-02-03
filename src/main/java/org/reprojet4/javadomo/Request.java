package org.reprojet4.javadomo;

import org.graalvm.compiler.api.replacements.Fold;

import javax.swing.*;
import java.sql.Connection;

public class Request {
    JTable DTable = new JTable();

    AmpConnect ampConnect = new AmpConnect();
    CamInstall camInstall = new CamInstall();
    Datamp datamp = new Datamp();
    Datatemp datatemp = new Datatemp();
    Food food = new Food();
    PersonalUser personalUser = new PersonalUser();
    Photo photo = new Photo();
    Room room = new Room();
    Sensor sensor = new Sensor();
    ThermoIntel thermoIntel = new ThermoIntel();

    public JTable Request(int id, String role, Connection co, int table){
        DTable.removeAll();
        DTable.revalidate();
        DTable.repaint();
        switch (table){
            case 0:
                DTable = ampConnect.Request(id, co, role);
                break;
            case 1:
                DTable = camInstall.Request(id, co, role);
                break;
            case 2:
                DTable = datamp.Request(id, co, role);
                break;
            case 3:
                DTable = datatemp.Request(id, co, role);
                break;
            case 4:
                DTable = food.Request(id, co, role);
                break;
            case 5:
                DTable = personalUser.Request(id, co, role);
                break;
            case 6:
                DTable = photo.Request(id, co, role);
                break;
            case 7:
                DTable = room.Request(id, co, role);
                break;
            case 8:
                DTable = sensor.Request(id, co, role);
                break;
            case 9:
                DTable = thermoIntel.Request(id, co, role);
                break;
            default:
                break;
        }
        return DTable;
    }
}
