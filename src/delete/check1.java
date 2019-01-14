/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author govind
 */
public class check1 {
     private final StringProperty year;
 private final   StringProperty month;
    private final   StringProperty bill;
   
     
       
          private final   IntegerProperty total;
          
          public check1(String bill,String month,String year,int total)
          {
              this.month= new SimpleStringProperty(month);
               this.year= new SimpleStringProperty(year);
                this.bill= new SimpleStringProperty(bill);
      // this.year= new SimpleIntegerProperty(year);
        
       //  this.fine= new SimpleFloatProperty(fine);
          this.total= new SimpleIntegerProperty(total);
          }
            public String getMonth() {return month.get();}
    //public String getHostel() {return hostel.get();}
    public String getYear() {return year.get();}
  public String getBill() {return bill.get();}
  //  public Float getFine() {return fine.get();}
    public Integer getTotal() {return total.get();}
         
      public void setTotal(int value)
    {
        total.set(value);
    }
        
    
 
          public void setMonth(String value)
    {
        month.set(value);
    }
                 public void setYear(String value)
    {
        year.set(value);
    }
                        public void setBill(String value)
    {
        bill.set(value);
    }
}
