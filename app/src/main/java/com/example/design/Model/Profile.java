package com.example.design.Model;

public class Profile {

    private String company_name;
    private String email;
    private String phonenumber;
    private String profession;
    private String country;

    public Profile(String company_name, String email, String phonenumber, String profession, String country) {
        this.company_name = company_name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.profession = profession;
        this.country = country;
    }
    public Profile(){

    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
