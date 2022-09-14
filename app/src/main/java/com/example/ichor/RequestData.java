package com.example.ichor;

public class RequestData {
    private String fullname,phoneno,bloodgroup,age,gender,requirement;

    public RequestData(String fullname, String phoneno, String bloodgroup, String age, String gender, String requirement) {
        this.fullname = fullname;
        this.phoneno = phoneno;
        this.bloodgroup = bloodgroup;
        this.age = age;
        this.gender = gender;
        this.requirement = requirement;
    }

    public RequestData() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
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

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
}
