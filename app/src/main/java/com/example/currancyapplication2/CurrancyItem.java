package com.example.currancyapplication2;

public class CurrancyItem {

    private String ID;
    private String NumeCode;
    private String CharCode;
    private int Normal;
    private String Name;
    private double Value;
    private double Previous;


    @Override
    public String toString() {
        return "CurrancyItem{" +
                "ID='" + ID + '\'' +
                ", NumeCode='" + NumeCode + '\'' +
                ", CharCode='" + CharCode + '\'' +
                ", Normal=" + Normal +
                ", Name='" + Name + '\'' +
                ", Value=" + Value +
                ", Previous=" + Previous +
                '}';
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNumeCode() {
        return NumeCode;
    }

    public void setNumeCode(String numeCode) {
        NumeCode = numeCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public void setCharCode(String charCode) {
        CharCode = charCode;
    }

    public int getNormal() {
        return Normal;
    }

    public void setNormal(int normal) {
        Normal = normal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    public double getPrevious() {
        return Previous;
    }

    public void setPrevious(double previous) {
        Previous = previous;
    }

}