package com.example.ruanjian.beans;

public class Unit {
    String unit1;
    String unit2;
    String unit3;

    @Override
    public String toString() {
        return "unit{" +
                "unit1='" + unit1 + '\'' +
                ", unit2='" + unit2 + '\'' +
                ", unit3='" + unit3 + '\'' +
                '}';
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public String getUnit3() {
        return unit3;
    }

    public void setUnit3(String unit3) {
        this.unit3 = unit3;
    }
}
