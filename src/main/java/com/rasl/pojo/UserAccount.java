package com.rasl.pojo;

public class UserAccount {
    private Integer appSecurityAccountId;
    private String login;
    private String firstName;
    private String middleName;
    private String lastName;


    public UserAccount() {
    }

    public UserAccount(Integer appSecurityAccountId, String login, String firstName, String middleName, String lastName) {
        this.appSecurityAccountId = appSecurityAccountId;
        this.login = login;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Integer getAppSecurityAccountId() {
        return appSecurityAccountId;
    }

    public void setAppSecurityAccountId(Integer appSecurityAccountId) {
        this.appSecurityAccountId = appSecurityAccountId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "AppSecurityAccount{" +
                "appSecurityAccountId=" + appSecurityAccountId +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
