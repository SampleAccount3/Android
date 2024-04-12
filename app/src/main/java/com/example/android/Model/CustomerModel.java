package com.example.android.Model;

public class CustomerModel {
    private int id;
    private String name;
    private String sample;
    private int age;
    private boolean isActiveCustomer;

    public CustomerModel(int id, String name,String sample, int age, boolean isActiveCustomer ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActiveCustomer = isActiveCustomer;
        this.sample = sample;
    }


    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sample='" + sample + '\'' +
                ", age=" + age +
                ", isActiveCustomer=" + isActiveCustomer +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActiveCustomer() {
        return isActiveCustomer;
    }

    public String getSample() {
        return sample;
    }
}
