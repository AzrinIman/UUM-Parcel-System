/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uum.parcel;

/**
 *
 * @author user
 */
public class Student {
    private String ownername,sendername,dateofarrival,phone,courier;
    final private String parcelID;
    
    
    Student(String ownername,String parcelID,String sendername,String dateofarrival,String phone,String courier){
        this.ownername = ownername;
        this.parcelID = parcelID;
        this.sendername = sendername;
        this.dateofarrival = dateofarrival;
        this.phone = phone;
        this.courier = courier;
    }

  
    void setOwnername(String ownername){
        this.ownername = ownername;
    }

    void setSendername(String sendername){
        this.sendername = sendername;
    }
    void setDateofarrival(String dateofarrival){
        this.dateofarrival = dateofarrival;
    }
    void setPhone (String phone){
        this.phone = phone;
    }    
    void setCourier (String courier){
        this.courier = courier;
    }
    
    String getOwnername(){
        return ownername;
    }
    String getParcelID(){
        return parcelID;
    }
    String getSendername(){
        return sendername;
    }
    String getDateofarrival(){
        return dateofarrival;
    }
    String getPhone(){
        return phone;
    }
    String getCourier(){
        return courier;
}

    
    }

    

