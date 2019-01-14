/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fee;

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

/**
 *
 * @author govind
 */
public class paidbill {

  private final StringProperty payid;
  private final StringProperty name;
 private final   StringProperty month;
    private final   IntegerProperty reg;
    private final   StringProperty year;
      private final   FloatProperty amount;
        private final   FloatProperty fine;
          private final   IntegerProperty total;
          private final   StringProperty date;
          private final   IntegerProperty id;
          private final FloatProperty totalunit;
          private final FloatProperty unitrate;
          private final   StringProperty hostel;
          private final   StringProperty room;
          
          
             private SimpleStringProperty value;
             private SimpleIntegerProperty value1;
             private SimpleFloatProperty value2;
    
   public paidbill (int id,int reg,String name,String hostel,String room,String month, String year,float totalunit,float unitrate, float amount, float fine,int total,String date,String payid)
   {
    this.reg= new SimpleIntegerProperty(reg);
     this.id= new SimpleIntegerProperty(id);
         this.name= new SimpleStringProperty(name);
       this.hostel= new SimpleStringProperty(hostel);
       this.room= new SimpleStringProperty(room);
      this.month= new SimpleStringProperty(month);
       this.year= new SimpleStringProperty(year);
        this.totalunit= new SimpleFloatProperty(totalunit);
         this.unitrate= new SimpleFloatProperty(unitrate);
        this.amount= new SimpleFloatProperty(amount);
         this.fine= new SimpleFloatProperty(fine);
          this.total= new SimpleIntegerProperty(total);
           this.date= new SimpleStringProperty(date);
           this.payid = new SimpleStringProperty(payid);
   }

  public String getPayid() {return payid.get();}
   public String getYear() {return year.get();}
    public Integer getReg() {return reg.get();}
    public String getMonth() {return month.get();}
    public String getHostel() {return hostel.get();}
    public String getRoom() {return room.get();}
    public Float getTotalunit() {return totalunit.get();}
    public Float getUnitrate() {return unitrate.get();}
    public Float getAmount() {return amount.get();}
    public Float getFine() {return fine.get();}
    public Integer getTotal() {return total.get();}
     public  String getDate() {return date.get();}
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
     public void setDate(String value)
    {
        date.set(value);
    }
      public void setTotal(int value)
    {
        total.set(value);
    }
           public void setUnitrate(float value)
    {
        unitrate.set(value);
    }
                public void setFine(float value)
    {
        fine.set(value);
    }
      
       public void setTotalunit(float value)
    {
        totalunit.set(value);
    }
        public void setAmount(float value)
    {
        amount.set(value);
    }
         public void setYear(String value)
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
    public void setPayid(String value)
    {
    
    payid.set(value);}
    
          
    
}


