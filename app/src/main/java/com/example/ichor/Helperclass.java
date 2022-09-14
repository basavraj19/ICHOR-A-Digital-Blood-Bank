package com.example.ichor;

public class Helperclass {
    private String phoneno,username, password,age,gender,bloodgroup,fullname;

    public Helperclass() {}

    public Helperclass(String username, String password, String phoneno, String gender,String age, String bloodgroup, String fullname) {
        this.phoneno = phoneno;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.bloodgroup = bloodgroup;
        this.fullname = fullname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
