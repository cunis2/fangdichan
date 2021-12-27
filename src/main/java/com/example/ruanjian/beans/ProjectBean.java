package com.example.ruanjian.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectBean {
    String pName;
    int pId;
    int cId;
    String dataUrl;
    String state;
    double price;
    String mPrinciple;
    String aPrinciple;
    String lPrinciple;
    String bTime;
    String eTime;
    String type;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getmPrinciple() {
        return mPrinciple;
    }

    public void setmPrinciple(String mPrinciple) {
        this.mPrinciple = mPrinciple;
    }

    public String getaPrinciple() {
        return aPrinciple;
    }

    public void setaPrinciple(String aPrinciple) {
        this.aPrinciple = aPrinciple;
    }

    public String getlPrinciple() {
        return lPrinciple;
    }

    public void setlPrinciple(String lPrinciple) {
        this.lPrinciple = lPrinciple;
    }

    public String getbTime() {
        return bTime;
    }

    public void setbTime(String bTime) {
        this.bTime = bTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
