package org.neo4j.ogm.domain.incorrect.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

public class Relationship {
    @StartNode
    private Person person;

    @EndNode
    private User user;
}
