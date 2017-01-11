package com.dataart.core.data;

public class Worker {

    private final Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Profession profession;
    private Integer phone;


    public Worker(Long id) {
        this.id = id;
    }

    public Worker(Long id, String firstName, String lastName, Integer age, Profession profession) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.profession = profession;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Profession getProfession() {
        return profession;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.id.toString() + " - " + this.firstName + " - " + this.lastName + " - " + this.profession.toString() + " - ";
    }

}
