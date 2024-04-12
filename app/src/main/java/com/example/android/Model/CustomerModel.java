package com.example.android.Model;

public class CustomerModel {
    private int id;
    private String name;
    private int age;
    private boolean isActiveCustomer;

    public CustomerModel(int id, String name, int age, boolean isActiveCustomer) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActiveCustomer = isActiveCustomer;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
}
