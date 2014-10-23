package com.eklib.desktopviewer.persistance.model;


import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by vadim on 17.09.2014.
 */
@Entity
public class User extends BaseEntity implements Serializable {

    private String firstName;
    private String lastName;

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
