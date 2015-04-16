package org.neo4j.ogm.domain.validations.Relationship.incorrect.MissingIdField;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.Transient;
import org.neo4j.ogm.domain.friendships.Person;

@RelationshipEntity
public class Relationship {

    @StartNode
    private Person person;

    @EndNode
    private Person friend;

    private int strength;

    public Relationship() {}

    public Relationship(Person from, Person to, int strength) {
        this.person = from;
        this.friend = to;
        this.strength = strength;
    }

    @EndNode
    public Person getPerson() {
        return person;
    }

    public Person getFriend() {
        return friend;
    }

    public int getStrength() {
        return strength;
    }

    @Transient
    public String foo() {
        return "bar";
    }

    @EndNode
    public void setPerson(Person person) { this.person = person; }
}
