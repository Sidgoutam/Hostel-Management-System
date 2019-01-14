/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewstudents;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author govind
 */
public class students {

    public static Object getid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    private final   StringProperty name;
    private final   IntegerProperty reg;
    private final   StringProperty roll;
      private final   StringProperty father;
        private final   StringProperty mother;
          private final   StringProperty mobile;
          private final   StringProperty hostel;
          private final   IntegerProperty id;
          private final   StringProperty address;
          private final   StringProperty guardian;
          private final   StringProperty city;
          private final   StringProperty state;
          private final   StringProperty dob;
          private final   StringProperty gender;
          private final   StringProperty email;
          private final   StringProperty room;
          
          
          
          
          
          
          
    
    private SimpleStringProperty value;
    
    /**
     *
     * @param id
     * @param hostel
     * @param room
     * @param reg
     * @param name
     * @param gender
     * @param father
     * @param mother
     * @param roll
     * @param dob
     * @param mobile
     * @param guardian
     * @param address
     * @param city
     * @param state
     * @param email
     */
    public students (int id,String hostel,String room, int reg, String name, String gender,String father, String mother,String roll,String dob,String mobile,String guardian,String address,String city,String state,String email)
    {
        this.name= new SimpleStringProperty(name);
        this.reg= new SimpleIntegerProperty(reg);
        this.roll= new SimpleStringProperty(roll);
        this.father= new SimpleStringProperty(father);
        this.mother= new SimpleStringProperty(mother);
        this.mobile = new SimpleStringProperty(mobile);
         this.address = new SimpleStringProperty(address);
          this.city = new SimpleStringProperty(city);
           this.state = new SimpleStringProperty(state);
            this.email = new SimpleStringProperty(email);
             this.guardian = new SimpleStringProperty(guardian);
              this.gender = new SimpleStringProperty(gender);
               this.dob = new SimpleStringProperty(dob);
                this.hostel = new SimpleStringProperty(hostel);
                 this.room = new SimpleStringProperty(room);
                  this.id = new SimpleIntegerProperty(id);
                  
    }

    students(String string0, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12, String string13, String string14) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public students(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getName() {return name.get();}
    public Integer getReg() {return reg.get();}
    public String getRoll() {return roll.get();}
    public String getMobile() {return mobile.get();}
    public String getFather() {return father.get();}
    public String getMother() {return mother.get();}
     public String getHostel() {return hostel.get();}
    public Integer getId() {return id.get();}
    public String getRoom() {return room.get();}
    public String getDob() {return dob.get();}
    public String getCity() {return city.get();}
    public String getAddress() {return address.get();}
    
     public String getState() {return state.get();}
    public String getEmail() {return email.get();}
    public String getGender() {return gender.get();}
    public String getGuardian() {return guardian.get();}
    
    
    public void setName(String value)
    {
        //this.value =new SimpleStringProperty(value);
        name.set(value);
    }
    public void setReg(int value)
    {
        reg.set(value);
    }
    public void setRoll(String value)
    {
        roll.set(value);
    }
     public void setFather(String value)
    {
        father.set(value);
    }
      public void setMother(String value)
    {
        mother.set(value);
    }
          public void setGender(String value)
    {
        //this.value =new SimpleStringProperty(value);
        gender.set(value);
    }
       public void setMobile(String value)
    {
        mobile.set(value);
    }
           public void setId(int value)
    {
        //this.value =new SimpleStringProperty(value);
        id.set(value);
    }
               public void setHostel(String value)
    {
        //this.value =new SimpleStringProperty(value);
        hostel.set(value);
    }
                   public void setRoom(String value)
    {
        //this.value =new SimpleStringProperty(value);
        room.set(value);
    }    public void setGuardian(String value)
    {
        //this.value =new SimpleStringProperty(value);
        guardian.set(value);
    }
        public void setDob(String value)
    {
        //this.value =new SimpleStringProperty(value);
        dob.set(value);
    }
            public void setAddress(String value)
    {
        //this.value =new SimpleStringProperty(value);
        address.set(value);
    }
                public void setCity(String value)
    {
        //this.value =new SimpleStringProperty(value);
        city.set(value);
    }
                    public void setState(String value)
    {
        //this.value =new SimpleStringProperty(value);
        state.set(value);
    }
                        public void setEmail(String value)
    {
        //this.value =new SimpleStringProperty(value);
        email.set(value);
    }
                        
                   
    
   

}

