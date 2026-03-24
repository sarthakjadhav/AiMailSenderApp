package com.app.mailSender.dto;

public class MailRequest {

    private String to;
    private String company;
    private String position;

    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        if (company == ""){
            this.company ="";
            return;
        }
        this.company = company;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        if (position == ""){
            this.position ="";
            return;
        }
        this.position = position;
    }
}