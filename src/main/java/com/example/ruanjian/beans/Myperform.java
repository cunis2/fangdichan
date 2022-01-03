package com.example.ruanjian.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Myperform {
    int eId;
    String name;
    String duty;
    String department;
    String type;
    double baseMoney;
    double performMoney;
    double totalMoney;
    String evaluate;

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBaseMoney() {
        return baseMoney;
    }

    public void setBaseMoney(double baseMoney) {
        this.baseMoney = baseMoney;
    }

    public double getPerformMoney() {
        return performMoney;
    }

    public void setPerformMoney(double performMoney) {
        this.performMoney = performMoney;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }
}
