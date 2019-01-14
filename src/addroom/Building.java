package addroom;


import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sidharth
 */
public class Building {
    
    private SimpleStringProperty Building_No;
    private SimpleStringProperty Room_No;

    public Building(String Building_No, String Room_No) {
        this.Building_No = new SimpleStringProperty(Building_No);
        this.Room_No = new SimpleStringProperty(Room_No);
    }

    /**
     * @return the Building_No
     */
    public String getBuilding_No() {
        return Building_No.get();
    }

    /**
     * @param Building_No the Building_No to set
     */
    public void setBuilding_No(String Building_No) {
        this.Building_No = new SimpleStringProperty(Building_No);
    }

    /**
     * @return the Room_No
     */
    public String getRoom_No() {
        return Room_No.get();
    }

    /**
     * @param Room_No the Room_No to set
     */
    public void setRoom_No(String Room_No) {
        this.Room_No = new SimpleStringProperty(Room_No);
    }
    
    
}
