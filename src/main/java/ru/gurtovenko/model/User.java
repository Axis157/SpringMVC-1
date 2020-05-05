package ru.gurtovenko.model;

import javax.validation.constraints.NotBlank;

public class User {
    @NotBlank(message = "Not required")
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String email;

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public User() {
    }

    public void setName(String login) {
        this.name = login;
    }

    public void setSurname(String password) {
        this.surname = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
}
