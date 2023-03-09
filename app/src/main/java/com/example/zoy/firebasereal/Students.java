package com.example.zoy.firebasereal;

public class Students {
    private String id;
    private String email;
    private String name;
    private String Password;
    public Students(String id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.Password = password;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public Students(String email,String name,String password){
           this.email=email;
           this.name=name;
           this.Password=password;
    }
    public Students(){
    }
    public String toString(){
        return this.id + "." + name + "- "+ email;
    }
}
