package org.neo4j.ogm.domain.incorrect.inheritance;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label ="User")
public class User extends Person {

    private String firstName;
    private String lastName;

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