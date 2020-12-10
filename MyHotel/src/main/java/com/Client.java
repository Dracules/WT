package com;

public class Client {
    private int id;
    private String surname;
    private String name;
    private String email;
    private String passwordHash;

    public Client(int id, String surname, String name, String email, String passwordHash)
    {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Client(String surname, String name, String email, String passwordHash)
    {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
