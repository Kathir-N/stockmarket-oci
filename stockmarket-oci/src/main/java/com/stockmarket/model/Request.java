package com.stockmarket.model;



public class Request {

    Company registration;

    public Request(){};

    public Request(Company registerRequest){
        this.registration = registerRequest;
    }

    public Company getCompany() {
        return registration;
    }

    public void setCompany(Company Registration) {
        this.registration = Registration;
    }
    
}