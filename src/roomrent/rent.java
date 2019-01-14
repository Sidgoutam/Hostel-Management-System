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
public class rent {


  private final StringProperty name;
 private final   StringProperty month;
    private final   IntegerProperty reg;
    private final   IntegerProperty year;
     
        private final   FloatProperty fine;
          private final   IntegerProperty total;
          private final   StringProperty lastdate;
           private final   StringProperty paid;
          private final   IntegerProperty id;
         
          private final   StringProperty hostel;
          private final   StringProperty room;
          
          
             private SimpleStringProperty value;
             private SimpleIntegerProperty value1;
             private SimpleFloatProperty value2;
    
                   public rent (int id,int reg,String name,String hostel,String room,String month,int year,int fine,int total,String lastdate,String paid)
   {
    this.reg= new SimpleIntegerProperty(reg);
     this.id= new SimpleIntegerProperty(id);
         this.name= new SimpleStringProperty(name);
       this.hostel= new SimpleStringProperty(hostel);
       this.room= new SimpleStringProperty(room);
      this.month= new SimpleStringProperty(month);
       this.year= new SimpleIntegerProperty(year);
        
         this.fine= new SimpleFloatProperty(fine);
          this.total= new SimpleIntegerProperty(total);
           this.lastdate= new SimpleStringProperty(lastdate);
           this.paid = new SimpleStringProperty(paid);
   }
   public rent (int id,int reg,String name,String hostel,String room,String month,int year,int fine,int total,String lastdate)
   {
    this.reg= new SimpleIntegerProperty(reg);
     this.id= new SimpleIntegerProperty(id);
         this.name= new SimpleStringProperty(name);
       this.hostel= new SimpleStringProperty(hostel);
       this.room= new SimpleStringProperty(room);
      this.month= new SimpleStringProperty(month);
       this.year= new SimpleIntegerProperty(year);
        
         this.fine= new SimpleFloatProperty(fine);
          this.total= new SimpleIntegerProperty(total);
           this.lastdate= new SimpleStringProperty(lastdate);
         this.paid= new SimpleStringProperty("");
   }


  
   public Integer getYear() {return year.get();}
    public Integer getReg() {return reg.get();}
    public String getMonth() {return month.get();}
    public String getHostel() {return hostel.get();}
    public String getRoom() {return room.get();}
 
    public Float getFine() {return fine.get();}
    public Integer getTotal() {return total.get();}
     public  String getLastdate() {return lastdate.get();}
       public  String getName() {return name.get();}
         public  String getPaid() {return paid.get();}

    /**
     *
     * @return
     */
    public  Integer getId() {return id.get();}

    public void setReg(int value)
    {
        reg.set(value);
    }
     public void setPaid(String value)
    {
        paid.set(value);
    }
    public void setName(String value)
    {
        name.set(value);
    }
     public void setLastdate(String value)
    {
        lastdate.set(value);
    }
      public void setTotal(int value)
    {
        total.set(value);
    }
        
                public void setFine(float value)
    {
        fine.set(value);
    }
      
 
         public void setYear(int value)
    {
        year.set(value);
    }
          public void setMonth(String value)
    {
        month.set(value);
    } public void setId(int value1)
    {
        id.set(value1);
    }
    
          
    
}
