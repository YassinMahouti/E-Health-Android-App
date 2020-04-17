package com.example.iliasspush;

public class Profile {
    private String name;
    private int age;

    public Profile(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void showInfo(){
        System.out.println(name + " " + age);
    }
}
