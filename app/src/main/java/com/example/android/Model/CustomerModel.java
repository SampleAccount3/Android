package com.example.android.Model;

public class CustomerModel {
    private int id;
    private String name;
    private String sample;
    private int age;
    private boolean isActiveCustomer;
    private int income;
    private String dateCreated;

    public CustomerModel(int id, String name, String sample, int age, boolean isActiveCustomer, int income, String dateCreated) {
        this.id = id;
        this.name = name;
        this.sample = sample;
        this.age = age;
        this.isActiveCustomer = isActiveCustomer;
        this.income = income;
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sample='" + sample + '\'' +
                ", age=" + age +
                ", isActiveCustomer=" + isActiveCustomer +
                ", income=" + income +
                ", dateCreated='" + dateCreated + '\'' +
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

    public int getIncome() {
        return income;
    }

    public String getDateCreated() {
        return dateCreated;
    }
}
