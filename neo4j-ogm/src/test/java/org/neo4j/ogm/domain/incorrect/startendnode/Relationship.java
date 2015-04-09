package org.neo4j.ogm.domain.incorrect.startendnode;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity
public class Relationship {
    @StartNode
    @EndNode
    private User user;
}
