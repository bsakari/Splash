package com.example.emobilis.elimuassistant;

public class Students {
        String name, marks, reg,key;


    public Students(String name, String marks, String reg, String key) {
        this.name = name;
        this.marks = marks;
        this.reg = reg;
        this.key = key;
    }

    public Students() {
        }

    public String getName() {
        return name;
    }

    public String getMarks() {
        return marks;
    }

    public String getReg() {
        return reg;
    }

    public String getKey() {
        return key;
    }
}
