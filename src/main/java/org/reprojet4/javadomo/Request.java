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

    public JTable Request(int id, String role, Connection co, int table, int orderby){
        DTable.removeAll();
        DTable.revalidate();
        DTable.repaint();
        switch (table){
            case 0:
                DTable = ampConnect.Request(co, orderby);
                break;
            case 1:
                DTable = camInstall.Request(co, orderby);
                break;
            case 2:
                DTable = datamp.Request(co, orderby);
                break;
            case 3:
                DTable = datatemp.Request(co, orderby);
                break;
            case 4:
                DTable = food.Request(co, orderby);
                break;
            case 5:
                DTable = personalUser.Request(co, orderby);
                break;
            case 6:
                DTable = photo.Request(co, orderby);
                break;
            case 7:
                DTable = room.Request(co, orderby);
                break;
            case 8:
                DTable = sensor.Request(co, orderby);
                break;
            case 9:
                DTable = thermoIntel.Request(co, orderby);
                break;
            default:
                break;
        }
        return DTable;
    }
}
