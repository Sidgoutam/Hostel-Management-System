/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomrent;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author govind
 */
public class checkrent {


  private final StringProperty name;
 private final   StringProperty month;
    private final   IntegerProperty reg;
   
     
       
          private final   IntegerProperty total;
        
          private final   IntegerProperty id;
         
         
          private final   StringProperty room;
          
          
             private SimpleStringProperty value;
             private SimpleIntegerProperty value1;
             private SimpleFloatProperty value2;
    
   public checkrent (int id,int reg,String name,String room,String month,int total)
   {
    this.reg= new SimpleIntegerProperty(reg);
     this.id= new SimpleIntegerProperty(id);
         this.name= new SimpleStringProperty(name);
       //this.hostel= new SimpleStringProperty(hostel);
       this.room= new SimpleStringProperty(room);
      this.month= new SimpleStringProperty(month);
      // this.year= new SimpleIntegerProperty(year);
        
       //  this.fine= new SimpleFloatProperty(fine);
          this.total= new SimpleIntegerProperty(total);
          // this.lastdate= new SimpleStringProperty(lastdate);
   }

  
  // public Integer getYear() {return year.get();}
    public Integer getReg() {return reg.get();}
    public String getMonth() {return month.get();}
    //public String getHostel() {return hostel.get();}
    public String getRoom() {return room.get();}
 
  //  public Float getFine() {return fine.get();}
    public Integer getTotal() {return total.get();}
    // public  String getLastdate() {return lastdate.get();}
       public  String getName() {return name.get();}

    /**
     *
     * @return
     */
    public  Integer getId() {return id.get();}

    public void setReg(int value)
    {
        reg.set(value);
    }
    public void setName(String value)
    {
        name.set(value);
    }
     
      public void setTotal(int value)
    {
        total.set(value);
    }
        
    
 
          public void setMonth(String value)
    {
        month.set(value);
    } public void setId(int value1)
    {
        id.set(value1);
    }
    
          
    
}
