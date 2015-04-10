package org.neo4j.ogm.domain.incorrect.relationship;

import org.neo4j.ogm.annotation.Transient;

@Transient
public class Person {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

}