package com.example.a28jsonparsingusinggson.dto;

import java.util.ArrayList;

/**
 * Created by DELL on 28-01-2017.
 */

public class My {

    private String Name;
    private String OS;
    private double Ver;
    private boolean IsUpdateAva;
    private Versions AllVersions;
    private ArrayList<Devices> devices;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public double getVer() {
        return Ver;
    }

    public void setVer(double ver) {
        Ver = ver;
    }

    public boolean isUpdateAva() {
        return IsUpdateAva;
    }

    public void setUpdateAva(boolean updateAva) {
        IsUpdateAva = updateAva;
    }

    public Versions getAllVersions() {
        return AllVersions;
    }

    public void setAllVersions(Versions allVersions) {
        AllVersions = allVersions;
    }

    public ArrayList<Devices> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Devices> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "My{" +
                "Name='" + Name + '\'' +
                ", OS='" + OS + '\'' +
                ", Ver=" + Ver +
                ", IsUpdateAva=" + IsUpdateAva +
                ", AllVersions=" + AllVersions +
                ", devices=" + devices +
                '}';
    }
}
